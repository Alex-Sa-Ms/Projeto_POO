public interface Player{
    //Esta interface podia ser dividada em duas (uma que obedeça ás necessidades da classe Jogo, e outra que obedeça ás da classe Equipa)
    //de modo a que não seja necessário escrever métodos dispensáveis para algumas das classes

    //Unica Parte Comum ás duas classes
    Player Clone();


    //Parte necessária para reutilizar a classe 'Equipa'

    /* Calcula a pontuação geral de um jogador */
    int getOverall();

    /*  De modo a manter o código reutilizável é necessário usar um método que seria tipico de C.
        Calcula a pontuação geral de um jogador em função da posicao e a area de jogo.
    *  Posicao:
    *   - Guarda-Redes:       0
    *   - Defesa(/lateral):   1
    *   - Medio:              2
    *   - Avancado:           3
    *  Area de Jogo:
    *   - Esquerda:     0
    *   - Centro:       1
    *   - Direita:      2
    * */
    int getOverall(int pos, int area);

    int getShirtNumber();

    void setShirtNumber(int shirtNumber);


    //Parte necessária para reutilizar a classe 'Jogo'

    String getName();

    int getStrike();

    int getHeadGame();

    //String que retorna o nome, o número de camisola e o overall numa string
    default String getGenericInfo(){
        return "Name: " + this.getName() + "   Shirt Number: " + this.getShirtNumber() + "   Overall: " + this.getOverall();
    }
}
