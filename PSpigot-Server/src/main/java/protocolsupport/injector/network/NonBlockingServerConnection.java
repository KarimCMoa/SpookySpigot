package protocolsupport.injector.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.server.ChatComponentText;
import net.minecraft.server.CrashReport;
import net.minecraft.server.CrashReportSystemDetails;
import net.minecraft.server.LazyInitVar;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.NetworkManager;
import net.minecraft.server.PacketPlayOutKickDisconnect;
import net.minecraft.server.ReportedException;
import net.minecraft.server.ServerConnection;
import org.apache.logging.log4j.Logger;
import org.spigotmc.SpigotConfig;
import protocolsupport.utils.Utils;

public class NonBlockingServerConnection
        extends ServerConnection
{
    private static Logger e;
    private List<ChannelFuture> g;
    private List<NetworkManager> h;

    public NonBlockingServerConnection()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
        super(MinecraftServer.getServer());
        e = (Logger)((Field)Utils.setAccessible(ServerConnection.class.getDeclaredField("e"))).get(null);
        this.g = ((List<ChannelFuture>)((Field)Utils.setAccessible(ServerConnection.class.getDeclaredField("g"))).get(this));
        this.h = new ConcurrentLinkedQueueFakeListImpl<>();
        ((Field)Utils.setAccessible(ServerConnection.class.getDeclaredField("h"))).set(this, this.h);
    }

    public static void inject()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
    {
        ((Field)Utils.setAccessible(MinecraftServer.class.getDeclaredField("q"))).set(MinecraftServer.getServer(), new NonBlockingServerConnection());
    }

    public void a(InetAddress inetaddress, int port)
            throws IOException
    {
        synchronized (this.g)
        {
            Class<? extends ServerSocketChannel> oclass;
            LazyInitVar<? extends EventLoopGroup> lazyinitvar;
            if ((Epoll.isAvailable()) && (MinecraftServer.getServer().ai()))
            {
                oclass = EpollServerSocketChannel.class;
                lazyinitvar = ServerConnection.b;
                e.info("Using epoll channel type");
            }
            else
            {
                oclass = NioServerSocketChannel.class;
                lazyinitvar = ServerConnection.a;
                e.info("Using default channel type");
            }
            this.g.add(

                    ((ServerBootstrap)((ServerBootstrap)new ServerBootstrap().channel(oclass)).childHandler(new ServerConnectionChannel(this.h)).group((EventLoopGroup)lazyinitvar.c()).localAddress(inetaddress, port))
                            .bind().syncUninterruptibly());
        }
    }

    public void c()
    {
        if ((SpigotConfig.playerShuffle > 0) && (MinecraftServer.currentTick % SpigotConfig.playerShuffle == 0)) {
            Collections.shuffle(this.h);
        }
        Iterator<NetworkManager> iterator = this.h.iterator();
        while (iterator.hasNext())
        {
            final NetworkManager networkmanager = (NetworkManager)iterator.next();
            if (!networkmanager.h()) {
                if (!networkmanager.g())
                {
                    if (!networkmanager.preparing)
                    {
                        iterator.remove();
                        networkmanager.l();
                    }
                }
                else {
                    try
                    {
                        networkmanager.a();
                    }
                    catch (Exception exception)
                    {
                        if (networkmanager.c())
                        {
                            CrashReport crashreport = CrashReport.a(exception, "Ticking memory connection");
                            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Ticking connection");
                            crashreportsystemdetails.a("Connection", new Callable()
                            {
                                public String call()
                                        throws Exception
                                {
                                    return networkmanager.toString();
                                }
                            });
                            throw new ReportedException(crashreport);
                        }
                        e.warn("Failed to handle packet for " + networkmanager.getSocketAddress(), exception);
                        final ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");
                        networkmanager.a(new PacketPlayOutKickDisconnect(chatcomponenttext), (GenericFutureListener) future -> networkmanager.close(chatcomponenttext), new GenericFutureListener[0]);

                        networkmanager.k();
                    }
                }
            }
        }
    }

    public static class ConcurrentLinkedQueueFakeListImpl<E>
            extends ConcurrentLinkedQueue<E>
            implements List<E>
    {
        private static final long serialVersionUID = -8466302736959675653L;

        public boolean addAll(int index, Collection<? extends E> c)
        {
            throw new UnsupportedOperationException();
        }

        public E get(int index)
        {
            throw new UnsupportedOperationException();
        }

        public E set(int index, E element)
        {
            throw new UnsupportedOperationException();
        }

        public void add(int index, E element)
        {
            throw new UnsupportedOperationException();
        }

        public E remove(int index)
        {
            throw new UnsupportedOperationException();
        }

        public int indexOf(Object o)
        {
            throw new UnsupportedOperationException();
        }

        public int lastIndexOf(Object o)
        {
            throw new UnsupportedOperationException();
        }

        public ListIterator<E> listIterator()
        {
            throw new UnsupportedOperationException();
        }

        public ListIterator<E> listIterator(int index)
        {
            throw new UnsupportedOperationException();
        }

        public List<E> subList(int fromIndex, int toIndex)
        {
            throw new UnsupportedOperationException();
        }
    }
}
