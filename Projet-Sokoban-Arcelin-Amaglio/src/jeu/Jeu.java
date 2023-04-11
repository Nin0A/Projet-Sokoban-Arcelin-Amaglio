package jeu;

public class Jeu {
    //déclarations des attributs
    private Perso perso;
    private ListeElements caisses;
    private ListeElements depots;
    private Labyrinthe laby;
    public final static String HAUT = "Haut";
    public final static String BAS = "Bas";
    public final static String GAUCHE = "Gauche";
    public final static String DROITE = "Droite";

    /**
     * Constructeur du jeu
     *
     * @param p  personnage
     * @param l  labirynthe
     * @param lc liste des caisses
     * @param ld liste des dépôts
     */
    Jeu(Perso p, Labyrinthe l, ListeElements lc, ListeElements ld) {
        this.perso = p;
        this.laby = l;
        this.caisses = lc;
        this.depots = ld;
    }

    /**
     * méthode JeuToString
     *
     * @return un String correspondant au labyrinthe
     */
    public String JeuToString() {
        String res = "";

        //on parcours tout notre labyrinthe de (HauteurMax à LargeurMax)
        for (int y = 0; y < laby.getHauteurMax(); y++) {
            for (int x = 0; x < laby.getLargeurMax(); x++) {
                res += getChar(x, y); //on ajoute les caractères correspondant
            }
            res += "\n";
        }
        return res;
    }

    /**
     * méthode getChar
     *
     * @param x abscisse
     * @param y ordonnée
     * @return un caractère correspondant à l'indice (x,y)
     */
    public char getChar(int x, int y) {
        char c = Labyrinthe.VIDE; //on initialise un char 'c' à VIDE

        //sinon suivant le cas on change la valeur de 'c'
        if (this.perso.getX() == x && this.perso.getY() == y) {
            c = Labyrinthe.PJ;
        } else if (this.caisses.etreElement(x, y)) {
            c = Labyrinthe.CAISSE;
        } else if (this.depots.etreElement(x, y)) {
            c = Labyrinthe.DEPOT;
        } else if (this.laby.etreMur(x, y)) {
            c = Labyrinthe.MUR;
        }
        return c;
    }

    /**
     * méthode getSuivant
     *
     * @param x      abscisse
     * @param y      ordonnée
     * @param action action
     * @return un tableau de int avec [x,y]
     * @throws ActionInconnueException
     */
    public int[] getSuivant(int x, int y, String action) throws ActionInconnueException {
        int[] coordonee = {x, y}; //on initialise notre tableau contenant [x,y] par les valeurs entrées en paramètres

        //suivant l'action les coordonées changes
        switch (action) {
            case Jeu.HAUT:
                coordonee[1] = y - 1;
                break;
            case Jeu.BAS:
                coordonee[1] = y + 1;
                break;
            case Jeu.GAUCHE:
                coordonee[0] = x - 1;
                break;
            case Jeu.DROITE:
                coordonee[0] = x + 1;
                break;
            default:
                System.out.println("Action non valide"); //si le switch ne gère par l'action demander, on affiche l'exception
                throw new ActionInconnueException("Action inconnue : " + action);
        }
        return coordonee;
    }

    /**
     * méthode déplacerPerso permet de déplcaer le personnage
     *
     * @param action action
     * @throws ActionInconnueException
     */
    public void deplacerPerso(String action) throws ActionInconnueException {

        //on récupère les coordonnées du personnage
        int x = this.perso.getX();
        int y = this.perso.getY();
        //on récupère les coordonnées générer par l'action voulu
        int[] suivant = getSuivant(x, y, action);

        //on attribue x et y à des variables temporaire si jamais l'action n'est pas possible
        int tmpX = x;
        int tmpY = y;

        // x et y prennent les valeurs de générer par getSuivant
        x = suivant[0];
        y = suivant[1];

        if (laby.etreMur(x, y)) { //cas si il y a un mur, alors personnage de bouge pas, il prend les valeurs sauvegarder dans les variables tmp
            this.perso.setX(tmpX);
            this.perso.setY(tmpY);
        } else {
            boolean bool = false; //boolean qui permet de savoir si le perso se déplace ou non (true si il y a un obstacle)
            if (this.caisses.getElement(x, y) != null) { //si il y a une caisse

                Element caisse = this.caisses.getElement(x, y); //on récupère l'élement aux coordonnées (x,y)
                int indiceElem = this.caisses.indiceElement(x, y); //on récupère l'indice de cette élément dans la liste (this.caisses)

                //De même que pour personnage on sauvegarde les valeurs initial si l'action ne peut se dérouler correctement
                int xElemTmp = this.caisses.getElement(x, y).getX();
                int yElemTmp = this.caisses.getElement(x, y).getY();

                //on récupère les coordonnées générer par getSuivant
                int[] suivantElem = getSuivant(xElemTmp, yElemTmp, action);

                //on initialise les variables xElem et yElem avec les valeurs générer par getSuivant
                int xElem = suivantElem[0];
                int yElem = suivantElem[1];

                if (laby.etreMur(xElem, yElem) || this.caisses.getElement(xElem, yElem) != null) {
                    //si après la caisse il y a un mur OU une caisse alors la caisse ne se déplace aps
                    caisse.setX(xElemTmp);
                    caisse.setY(yElemTmp);
                    bool = true; //le personnage ne peut pas bouger il y a un obstacle donc il gardes ses valeurs initial
                } else {
                    //sinon la caisse se déplace
                    caisse.setX(xElem);
                    caisse.setY(yElem);
                }

                //on met à jour les coordonnées de la caisse déplacer
                this.caisses.setListeElements(indiceElem, caisse);
            }
            if (bool) { //si bool=true alors le personnage ne peut pas avancer donc il garde ses valeurs initial
                this.perso.setX(tmpX);
                this.perso.setY(tmpY);
            } else { //sinon il se déplace
                this.perso.setX(x);
                this.perso.setY(y);
            }
        }
    }

    /**
     * méthode etreFini permet de créer une condition pour finir une partie du jeu
     *
     * @return true si le jeu est fini (soit les caisses sont sur tous les dépôts)
     */
    public boolean etreFini() {
        int compteur = 0;// initialisation d'un compteur

        //on parcours la liste des caisses & celle des dépôts
        for (int i = 0; i < this.caisses.getTaille(); i++) {
            for (int j = 0; j < this.depots.getTaille(); j++) {

                //si les coordonnées d'une caisse et d'un dépôts aux indice i et j corresponde alors le compteur augmente de 1
                if (this.caisses.getElementByIndice(i).getX() == this.depots.getElementByIndice(j).getX() && this.caisses.getElementByIndice(i).getY() == this.depots.getElementByIndice(j).getY()) {
                    compteur += 1;
                }
            }
        }
        return compteur == this.caisses.getTaille(); //true si le compteur correspond aux nombre total de caisses
    }

    /**
     * getter de Labyrinthe
     *
     * @return le labyrinthe
     */
    public Labyrinthe getLaby() {
        return this.laby;
    }

    public Perso getPerso() {
        return this.perso;
    }

    public ListeElements getListeCaisses() {
        return this.caisses;
    }

    public ListeElements getListeDepots() {
        return this.depots;
    }
}
