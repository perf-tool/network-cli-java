package io.github.network.cli;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CmdResolve {

    @Parameter(names = "--lib", description = "library to use")
    public ResolveLibEnum lib;

    @Parameter(names = "--host", description = "host to resolve")
    public String host;

}
