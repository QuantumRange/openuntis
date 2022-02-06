package de.quantumrange.openuntis.untis;

import org.jetbrains.annotations.NotNull;

public record UntisAccess(@NotNull String server,
						  @NotNull String school,
						  @NotNull String user,
						  @NotNull String password) {

}
