# Netty学习

## java的IO模型
IO模型：用什么样的数据流通道进行数据的发送和接收，不同的IO模型决定了程序通信的性能。
目前java支持3种网络编程IO模型：分别是BIO、NIO、AIO

### BIO (同步阻塞)
BIO是JDK1.4版本之前常用的编程方式。
服务实现模式为一个连接创建一个线程，即客户端有连接请求时，服务端就需要启动一个线程进行处理，
如果这个连接不做任何事情就会一直阻塞着，造成不必要的线程开销。
虽然可以用线程池优化技术，但是还是避免不了客户端连接请求创建一个线程资源的局面。

BIO方式适用于连接数量比较小且固定的架构，这种方式对服务器资源要求比较高，并发局限于应用仲，
但程序简单易理解。

### NIO (同步非阻塞)
服务器实现模式为一个线程处理多个请求连接，客户端发送的连接请求都会注册到多路复用器上，
多路复用器轮询到连及恩有I/O请求就进行处理。

NIO方式适用于连接树木多且连接比较短(轻操作)的架构，比如聊天服务器，弹幕系统，服务器间通讯等。
编程比较复杂，JDK1.4开始支持。

### AIO (异步非阻塞)
AIO的特点是先由操作系统完成后才通知服务端程序启动线程去处理，一般适用于连接数多且连接时间较长的应用。

当进行读写的时候，只需要直接调用API的read/write方法即可。这两种方法都是异步的。

对于读操作，当有流可读取的时候，操作系统会将可读的流传入read方法的缓冲区，并通知应用程序；

对于写操作，当操作系统将write方法传递的流写入完毕时，操作系统会主动通知应用程序。

所以，可以理解为，read/write方法都是异步的，完成后会主动回调函数。

在JDK7中，也被称作NIO2。

AIO方法使用于连接树木多且连接比较长（重操作）的架构，比如相册服务器，充分调用OS(操作系统)参与并发操作，
编程比较复杂，JDK7开始支持。

### 同步/异步，阻塞/非阻塞
阻塞/非阻塞：进程/线程需要操作的数据如果尚未就绪，是否妨碍了当前进程/线程的后续操作。面向的对象是系统
同步/异步：数据如果尚未就绪，是否需要等待数据结果。勉县的对象是应用程序

## NIO详细介绍
### NIO的基本概念
* NIO: java non-blocking IO，是JDK1.4提供的新API，是同步非阻塞的。
* NIO有三个核心部分 Channel(通道)，Buffer(缓冲区)，Selector(选择器)
* NIO是面向缓冲区(Buffer)或者块编程的。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动，
这就增加了处理过程中灵活性，使它可以提供非阻塞式的高伸缩性网络。也就是说缓冲区实现了NIO的非阻塞模式。
* NIO的非阻塞模式：使一个线程从某通道发送请求或者读取数据，但是它仅能得到目前可用的数据，
如果目前没有数据可用时，就什么都不会获取，而不时保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做
其他的事情。非阻塞写也是如此，一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。
* NIO是可以做到用一个线程来处理多个操作的。假设有10000个请求过来，根据实际情况，可以分配50或者100个线程来处理。
不像之前的阻塞IO（如BIO）那样，非得分配10000个线程。

### NIO和BIO的比较
* BIO以流的方式处理数据，而NIO以块（缓冲区）的方式处理数据，块I/O的效率比流I/O高很多。
* BIO是阻塞的，NIO则是非阻塞的。
* BIO基于字节流和字符流进行操作，而NIO基于Channel（通道）和Buffer(缓冲区)进行操作，数据总是从通道读取到缓冲区中，
或者从缓冲区写如到通道中。Selector(选择器)用于监听多个通道的事件（比如：连接请求，数据到达等），
因此使用单个线程就可以监听多个客户端通道。

### Selector、Channel、Buffer的关系
* 一个selector对应一个线程，一个selector对应多个channel
* 程序切换到哪个channel是由事件决定的，Event就是一个重要的概念(事件驱动)
* channel是双向的，可以返回底层操作系统的情况，比如Linux，底层的操作系统通道就是双向的。
* 每个channel又都会对应一个Buffer
* Buffer就是一个内存块、缓冲区，底层是由数组是项的
* 数据的read、write是通过Buffer传输的，这个和BIO不同，BIO中要么是输入流(inputStream)，要么是输出流(outputStream)，
不能双向，但是NIO的Buffer是可以读也可以写，需要且只需要调用flip方法切换。

### Buffer
缓冲区本质上是一个可以读写数据的内存块，可以理解成是一个容器对象（含数组），该对象提供了一组API，
可以更轻松地使用内存块，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况。
而Channel提供从文件、网络读取数据的渠道，但是读取或写入的数据都必须经由Buffer。

#### Buffer类以及子类
* ByteBuffer: 存储字节数到缓冲区
* ShortBuffer: 存储字符串数据到缓冲区
* CharBuffer: 存储字符数据到缓冲区
* IntBuffer: 存储整数数据到缓冲区
* LongBuffer: 存储长整型数据到缓冲区
* DoubleBuffer: 存储小数到缓冲区
* FloatBuffer: 存储小数到缓冲区

#### Buffer缓冲区的4各核心属性

| 属性 | 描述 |
|--- | ---- |
| Capacity | 容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变 |
| Limit | 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的|
| Position | 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变该值，为下次读写做准备|
| Mark | 标记 |

#### ByteBuffer
ByteBuffer支持特定类型的put和get，特定类型的put时，相应的get时必须和put对应起来，否则无法获取正确的值：
```markdown
    // 创建一个Buffer
    ByteBuffer buffer = ByteBuffer.allocate(64);
    // 类型化方式放入数据
    buffer.putInt(88);
    buffer.putLong(6L);
    buffer.putChar('朱');
    buffer.putShort((short) 11);
    // 取出
    buffer.flip();
    System.out.println(buffer.getInt());
    System.out.println(buffer.getLong());
    System.out.println(buffer.getChar());
    System.out.println(buffer.getShort());
```

### Channel
#### NIO的通道与流的区别：
* BIO中的stream是单向的，例如FileInputStream对象只能读取数据的操作，而NIO中的通道(Channel)是双向的，可以读操作，也可以写操作。
* 通道可以实现异步读写数据
* 通道可以从缓冲读数据，也可以写数据到缓冲

#### 常用的Channel类
Channel在NIO中是一个接口，常用的Channel类有：
* FileChannel: 用于文件的数据读写
* DatagramChannel: 用于UDP的数据读写
* ServerSocketChannel (类似ServerSocket): 用于TCP的数据读写
* SocketChannel (类似Socket): 用于TCP的数据读写

### Selector
* Java的NIO，用非阻塞的IO方式。可以用一个线程，处理多个的客户端连接，
就会使用到Selector(选择器)。
* Selector能够检测多个注册的通道上是否有事件发生（注意：多个Channel
以事件的方式可以注册到一个Selector），如果事件发生，便获取事件然后针对
每个事件进行相应的处理。这样就可以只用一个单线程去管理多个通道，也就是
管理多个连接和请求。
* 只有在连接/通道真正有读写事件发生时，才会进行读写，就大大地减少了系统开销，
并且不必为每个连接都创建一个线程，不用去维护多个线程。
* 避免了多线程之间的上下文切换导致的开销

#### 抽象类Selector方法说明
```java
import java.io.Closeable;import java.nio.channels.SelectionKey;
public abstract class Selector implements Closeable {
    // 得到一个选择器对象 
    public static Selector open();
    // 监控所有组册的通道，当其中有IO操作可以进行时，将对应的SelectionKey
    // 加入到内部集合并返回，参数用来设置超时时间
    public int select(long timeout);
    // 从内部集合中得到所有的SelectionKey
    public Set<SelectionKey> selectedKeys();
}
```
```markdown
selector.select();//阻塞
selector.select(1000);//阻塞1000毫秒，在1000毫秒后返回
selector.wakeup();//唤醒selector
selector.selectNow();//不阻塞，立马返回
```

#### SelectionKey的相关API
```java
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
public abstract class SelectionKey {
    // 得到与之关联的Selector对象
    public abstract Selector selector();
    // 得到与之关联的通道
    public abstract SelectableChannel channel();
    // 得到与之关联的共享数据
    public final Object attachment();
    // 设置或改变监听事件
    public abstract SelectionKey interestOps(int ops);
    // 是否可以accept
    public final boolean isAcceptable();
    // 是否可以读
    public final boolean isReadable();
    // 是否可以写
    public final boolean isWritable();
}
```

#### NIO非阻塞网络编程原理分析
1. 当客户端连接时，会通过ServerSocketChannel得到SocketChannel
2. Selector进行监听select方法，返回有事件发生的通道的个数
3. 将socketChannel注册到Selector上，register(Selector sel, int ops),
一个elector桑可以注册多个SocketChannel
4. 组册后返回一个SelectionKey,会和该Selector关联(集合)，进一步得到各个
SelectionKey(有事件发生)
5. 在通过SelectionKey反响获取SocketChannel,方法channel()，可以通过得到的channel,
完成业务处理。

#### SocketChannel
网络IO通道，具体负责进行读写操作。NIO把缓冲区的数据写入通道，或者把通道里的数据读到缓冲区。
```markdown
// 得到一个SocketChannel通道
public static SocketChannel open();
// 设置阻塞或非阻塞模式，false表示采用非阻塞模式
public final SelectableChannel configureBlocking(boolean block);
// 连接服务器
public boolean connect(SocketAddress remote);
// 如果上面的方法连接失败，接下来就要通过该方法完成连接操作
public boolean finishConnect();
// 往通道里写数据
public int write(ByteBuffer src);
// 从通道里读数据
public int read(ByteBuffer dst);
// 组册一个选择器并设置监听事件，最后一个参数可以设置共享数据
public final SelectionKey register(Selector sel, int ops, Object att)
// 关闭通道
public final void close();
```

#### ServerSocketChannel在服务器端监听新的客户端Socket连接
```markdown
// 得到一个ServerSocketChannel 通道
public static ServerSocketChannel open();
// 设置服务器端端口号
public final ServerSocketChannel bind(SocketAddress local);
// 设置阻塞或非阻塞模式
public final SelectableChannel configureBlocking(boolean block);
// 接受一个连接，返回代表这个连接的通道对象
public SocketChannel accept();
// 注册一个选择器并设置监听事件
public final SelectionKey register(Selector sel, int ops)
```
### NIO零拷贝
* 零拷贝是网络编程的关键，很多性能优化都离不开
* 在java程序中，常用的零拷贝由mmap(内存映射)和sendFile。那么，他们在OS里，到底是怎么样的一个设计？
我们分析mmap和sendFile这两个零拷贝
* 零拷贝不是拷贝，只是从操作系统角度，是没有CPU拷贝

#### 传统IO拷贝分析
3次状态切换：
```markdown
1. 从用户态切换到内核态(kernel context)
2. 从内核态到用户态
3. 再从用户态到内核态
```
4次拷贝次数
```markdown
1. 第一次拷贝：硬件上的数据做一次DMA拷贝，拷贝到内核buffer(kernel buffer)
2. 第二次拷贝：从内核buffer拷贝到用户buffer(user buffer)--cpu copy
3. 第三次拷贝：从用户buffer拷贝到socket buffer--cpu copy
4. 第四次拷贝：从socket buffer再做一次DMA拷贝到协议栈(protocol engine)
```
```markdown
DMA: direct memory access 直接内存拷贝（不使用cpu copy）
```

#### mmap优化拷贝分析
mmap通过内存映射，将文件映射到内核缓冲区，同时，用户空间可以共享内核空间的数据。
这样，在进行网络传输时，就可以减少内核空间到用户空间的拷贝次数。

3次状态切换：
```markdown
1. 从用户态切换到内核态（kernel context）
2. 从内核态到用户态
3. 再从用户态到内核态
```
3次拷贝次数：
```markdown
1. 第一次拷贝：硬件上的数据做一次DMA拷贝，拷贝到内核buffer(kernel buffer)
2. 第二次拷贝：由于mmap userBuffer和内核buffer可以共享数据，所以此时就不会在
发生一次用户拷贝，因此数据可以直接在内核缓冲直接进行修改，修改完后再通过CPU拷贝到
socket buffer。 --cpu copy
3. 第三次拷贝：从socket buffer再做一次DMA拷贝到协议栈(protocol engine)
```

#### sendFile 优化
linux2.1版本提供了sendFile函数，其基本原理如下：数据根本不经过用户态，直接从内核缓冲区进入到socket buffer,
同时，由于和用户态完全不关，就减少了一次上下文切换。

2次状态切换：
```markdown
1. 从用户态切换到内核态(kernel context)
2. 从内核态到用户态
```
3次拷贝次数：
```markdown
1. 第一次拷贝：硬件上的数据做一次DMA拷贝，拷贝到内核buffer(kernel buffer)
2. 第二次拷贝：使用cpu copy从内核buffer拷贝到socket buffer --cpu copy
3. 第三次拷贝：从socket buffer再做一次DMA拷贝到协议栈(protocol engine)
```

#### sendFile 第2次优化
linux在2.4版本中，做了一些修改，避免了从内核缓冲区拷贝到Socket buffer的操作，直接拷贝到协议栈，从而再一次减少了数据拷贝。

2次状态切换：
```markdown
1. 从用户态切换到内核态(kernel context)
2. 从内核态到用户态
```
2次拷贝次数：
```markdown
1. 第一次拷贝：硬件上的数据做一次DMA拷贝，拷贝到内核buffer(kernel buffer)
2. 第二次拷贝：从内核buffer再做一次DMA拷贝到协议栈(protocol engine)
3. 这里其实有一次cpu拷贝kernel buffer -> socket buffer，但是，拷贝的信息很少，
比如length、offset，消耗低，可以忽略。
```
这就是真正的零拷贝。

#### 零拷贝总结
1. 零拷贝，是从操作系统的角度来说的。因为内核缓冲区之间，没有数据是重复的（只有kernel buffer有一份数据）。
2. 零拷贝不仅仅带来更少的数据复制，还能带来其他的性能优势，例如更少的上下文切换，
更少的CPU缓存伪共享以及无CPU校验和计算。

#### mmap和sendFile的区别
1. mmap时合小数据量读写，sendFile时合大文件传输。
2. mmap需要4次上下文切换，3次数据拷贝；sendFile需要3次上下文切换，最少2次数据拷贝。
3. sendFile可以利用DMA方式，减少CPU拷贝，mmap则不能(必须从内核拷贝到Socket缓冲区)