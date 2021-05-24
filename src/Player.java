public interface Player{
    //Esta interface podia ser dividada em duas (uma que obedeça ás necessidades da classe Jogo, e outra que obedeça ás da classe Equipa)
    //de modo a que não seja necessário escrever métodos dispensáveis para algumas das classes

    //Unica Parte Comum ás duas classes
    Player Clone();

    //Parte necessária para reutilizar a classe 'Equipa'
    int getShirtNumber();

    void setShirtNumber(int shirtNumber);

    /* Calcula a pontuação geral de um jogador em função da sua subclasse */
    int getOverall();

    //Parte necessária para reutilizar a classe 'Jogo'

    String getName();

    int getStrike();

    int getHeadGame();
}
