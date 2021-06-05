public class FootballManagerApp {
    public static void main(String[] args) {
        Catalogo catalogo       = new Catalogo();
        Controlador controlador = new Controlador(catalogo);
        Vista vista             = new Vista(controlador);

        catalogo.addObserver(controlador);
        controlador.addObserver(vista);
        vista.run();
    }
}
