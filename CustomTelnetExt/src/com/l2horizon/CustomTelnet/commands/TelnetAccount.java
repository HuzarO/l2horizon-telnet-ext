package com.l2horizon.CustomTelnet.commands;

import java.util.LinkedHashSet;
import java.util.Set;

import l2.authserver.Config;
import l2.authserver.accounts.Account;
import l2.authserver.crypt.PasswordHash;
import l2.gameserver.network.telnet.TelnetCommand;
import l2.gameserver.network.telnet.TelnetCommandHolder;

public class TelnetAccount implements TelnetCommandHolder {
    private final Set<TelnetCommand> commands;

    public TelnetAccount() {
    	Config.loadConfiguration();
    	
    	try {
			Config.initCrypt();
		} catch (Throwable e) {
			e.printStackTrace();
		}
    	
    	final PasswordHash passwordHash = Config.DEFAULT_CRYPT;
    	
        commands = new LinkedHashSet<>();

        commands.add(new TelnetCommand("account_create") {
            @Override
            public String getUsage() {
                return "account_create <account_name> <password> - Creates a new account with the specified password";
            }

            @Override
            public String handle(String[] args) {
                if (args.length < 2) {
                    return null; // Show usage
                }

                String accountName = args[0];
                String password = args[1];

                Account account = new Account(accountName);
                account.restore();

                // Check if account already exists
                if (account.getPasswordHash() != null) {
                    return "already_exists";
                }

                // Create new account
                try {
					account.setPasswordHash(passwordHash.encrypt(password));
				} catch (Exception e) {
					e.printStackTrace();
					return "invalid_password";
				}
                account.save(); // Creates user in database.
                
                account.setAccessLevel(0);
                account.update(); // Save again with appropriate access level.

                return "created";
            }
        });

        commands.add(new TelnetCommand("account_set_password") {
            @Override
            public String getUsage() {
                return "account_set_password <account_name> <new_password> - Sets a new password for the specified account";
            }

            @Override
            public String handle(String[] args) {
                if (args.length < 2) {
                    return null; // Show usage
                }

                String accountName = args[0];
                String password = args[1];

                Account account = new Account(accountName);
                account.restore();

                // Check if account exists
                if (account.getPasswordHash() == null) {
                    return "not_exists";
                }

                // Update password
                try {
					account.setPasswordHash(passwordHash.encrypt(password));
				} catch (Exception e) {
					e.printStackTrace();
					return "invalid_password";
				}
                account.update();

                return "password_updated";
            }
        });
    }

    @Override
    public Set<TelnetCommand> getCommands() {
        return commands;
    }
}
