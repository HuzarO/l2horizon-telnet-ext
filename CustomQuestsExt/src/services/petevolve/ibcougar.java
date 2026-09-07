package services.petevolve;

/** Baby Cougar (level 55+) into an Improved Baby Cougar: Baby Cougar Chime 6649 becomes Improved Cougar Chime 10312. */
public class ibcougar extends PetEvolution
{
	@Override
	protected int fromNpcId()
	{
		return 12782;
	}

	@Override
	protected int fromControlItemId()
	{
		return 6649;
	}

	@Override
	protected int toControlItemId()
	{
		return 10312;
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
