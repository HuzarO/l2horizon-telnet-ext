package services.petevolve;

/** Great Wolf (level 70+) into a Fenrir: Great Wolf Necklace 9882 becomes Fenrir Necklace 10426. */
public class fenrir extends PetEvolution
{
	@Override
	protected int fromNpcId()
	{
		return 16025;
	}

	@Override
	protected int fromControlItemId()
	{
		return 9882;
	}

	@Override
	protected int toControlItemId()
	{
		return 10426;
	}

	@Override
	protected int minLevel()
	{
		return 70;
	}

	@Override
	protected String wrongPetPage()
	{
		return "no_great_wolf.htm";
	}

	@Override
	protected String noLevelPage()
	{
		return "no_level_gw.htm";
	}

	@Override
	protected String successPage()
	{
		return "yes_wolf.htm";
	}
}
