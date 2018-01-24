public class Engine {
	UI u = new UI();
	private boolean running;
	private int cards;
	private int matched;

	public Engine() {
		running = true;
		cards = 0;
		matched = 0;
		u.intro();
	}
	public void gameLoop() {
		while (running) {
			u.drawGrid(cards);
			u.selectCard1();
			cards++;
			u.drawGrid(cards);
			u.selectCard2();
			cards++;
			u.drawGrid(cards);
			if (u.checkCard())
				matched++;
			checkStatus();
		}
	}
	public void checkStatus() {
		if (matched == 8) {
			running = false;
			u.end(cards);
		}
	}
}
