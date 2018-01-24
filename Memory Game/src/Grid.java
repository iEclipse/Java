import java.util.*;

public class Grid {
	private int size = 4;
	private AbstractCard[][] grid = new AbstractCard[size][size];

	public void initialize() {
		AbstractCard[] allCards = {new QuestionMarkCard(), new QuestionMarkCard(),
				new DashCard(), new DashCard(),
				new BangCard(), new BangCard(),
				new StarCard(), new StarCard(),
				new SlashCard(), new SlashCard(),
				new SharpCard(), new SharpCard(),
				new ModCard(), new ModCard(),
				new PlusCard(), new PlusCard()};
		List<AbstractCard> cardsList = Arrays.asList(allCards);
		Collections.shuffle(cardsList);
		allCards = cardsList.toArray(new AbstractCard[]{});
		int cardIndex = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[i].length; ++j) {
				grid[i][j] = allCards[cardIndex];
				++cardIndex;
			}
		}
	}
	public AbstractCard[][] getGrid() {
		return grid;
	}
}
