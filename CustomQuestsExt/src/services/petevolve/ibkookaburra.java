package services.petevolve;

/** Baby Kookaburra (level 55+) into an Improved Baby Kookaburra: Baby Kookaburra Ocarina 6650 becomes Improved Kookaburra Ocarina 10313. */
public class ibkookaburra extends PetEvolution
{
	@Override
	protected int fromNpcId()
	{
		return 12781;
	}

	@Override
	protected int fromControlItemId()
	{
		return 6650;
	}

	@Override
	protected int toControlItemId()
	{
		return 10313;
	}

	@Override
	protected int minLevel()
	{
		return 55;
	}

	@Override
	protected String wrongPetPage()
	{
		return "no_pet.htm";
	}

	@Override
	protected String noLevelPage()
	{
		return "no_level.htm";
	}

	@Override
	protected String successPage()
	{
		return "yes_pet.htm";
	}
}
