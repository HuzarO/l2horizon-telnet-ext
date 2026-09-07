package services.petevolve;

/** Wolf (level 55+) into a Great Wolf: Wolf Collar 2375 becomes Great Wolf Necklace 9882. */
public class wolfevolve extends PetEvolution
{
	@Override
	protected int fromNpcId()
	{
		return 12077;
	}

	@Override
	protected int fromControlItemId()
	{
		return 2375;
	}

	@Override
	protected int toControlItemId()
	{
		return 9882;
	}

	@Override
	protected int minLevel()
	{
		return 55;
	}

	@Override
	protected String wrongPetPage()
	{
		return "no_wolf.htm";
	}

	@Override
	protected String noLevelPage()
	{
		return "no_level.htm";
	}

	@Override
	protected String successPage()
	{
		return "yes_wolf.htm";
	}
}
