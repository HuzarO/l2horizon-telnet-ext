package services.petevolve;

import l2.gameserver.Config;
import l2.gameserver.model.Player;

/** Baby Buffalo (level 55+) into an Improved Baby Buffalo: Baby Buffalo Panpipe 6648 becomes Improved Buffalo Panpipe 10311. */
public class ibbuffalo extends PetEvolution
{
	@Override
	protected int fromNpcId()
	{
		return 12780;
	}

	@Override
	protected int fromControlItemId()
	{
		return 6648;
	}

	@Override
	protected int toControlItemId()
	{
		return 10311;
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

	@Override
	protected String classRefusedPage(Player player)
	{
		return Config.ALT_IMPROVED_PETS_LIMITED_USE && player.isMageClass() ? "no_class_w.htm" : null;
	}
}
