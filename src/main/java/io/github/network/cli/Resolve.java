package io.github.network.cli;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.resolver.dns.DnsNameResolver;
import io.netty.resolver.dns.DnsNameResolverBuilder;

public class Resolve {

    public static void resolve(CmdResolve cmdResolve) {
        switch (cmdResolve.lib) {
            case STD:
                stdResolve(cmdResolve.host);
                break;
            case NETTY:
                nettyResolve(cmdResolve.host);
                break;
            default:
                System.out.println("Unknown library: " + cmdResolve.lib);
        }
    }

    public static void nettyResolve(String host) {
        DnsNameResolverBuilder dnsNameResolverBuilder = new DnsNameResolverBuilder();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        dnsNameResolverBuilder.eventLoop(nioEventLoopGroup.next());
        dnsNameResolverBuilder.channelType(NioDatagramChannel.class);
        DnsNameResolver dnsNameResolver = dnsNameResolverBuilder.build();
        dnsNameResolver.resolve(host).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(future.getNow());
            } else {
                System.out.println("Failed to resolve: " + host);
            }
            nioEventLoopGroup.shutdownGracefully();
        });
    }

    public static void stdResolve(String host) {
    }

}
