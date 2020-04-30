# jazdump
javalangのクラス定義情報をクロステーブル形式で標準出力に出力するコマンド

# もとねた
script-sketch/java/00054-java-特定のクラスに含まれる定数メソッド一覧取得/

# ダウンロード

[jazdump](https://github.com/ukijumotahaneniarukenia/jazdump/releases/tag/1-0-0)

```
unizp nnn-1.0-SNAPSHOT-bin.zip

or

tar xvf nnn-1.0-SNAPSHOT-bin.tar.gz
```

# インストール

```
echo "alias jazdump=\"$(which java) -jar $(find $(pwd) -name "nnn-1.0-SNAPSHOT.jar")\"">>~/.bashrc
source ~/.bashrc
```

# ヘルプ
```
$jazdump
Usage
jazdump java.lang.Thread java.lang.ClassLoader
```

# IN

```
$jazdump java.lang.Thread java.lang.ClassLoader
```

# OUT

```
行番号	CCCCC-00-クラス名	CCCCC-01-定数名	MMMMM-00-クラス名	MMMMM-01-アクセス修飾子	MMMMM-02-戻り値の型	MMMMM-03-メソッド名	MMMMM-04-可変長引数があるか	MMMMM-05-引数の個数	MMMMM-06-型パラメータリスト	MMMMM-07-型パラメータ記号リスト	MMMMM-08-引数の型リスト	MMMMM-09-仮引数の変数名リスト
00001-java.lang.ClassLoader-00002-00001			java.lang.ClassLoader	public	java.lang.String	toString	false	0				
00002-java.lang.ClassLoader-00002-00002			java.lang.ClassLoader	public static	java.io.InputStream	getSystemResourceAsStream	false	1			java.lang.String	arg0
00003-java.lang.ClassLoader-00002-00003			java.lang.ClassLoader	public	java.lang.Class<?>	loadClass	false	1			java.lang.String	arg0
00004-java.lang.ClassLoader-00002-00004			java.lang.ClassLoader	public	boolean	equals	false	1			java.lang.Object	arg0
00005-java.lang.ClassLoader-00002-00005			java.lang.ClassLoader	public static	java.net.URL	getSystemResource	false	1			java.lang.String	arg0
00006-java.lang.ClassLoader-00002-00006			java.lang.ClassLoader	public static	java.lang.ClassLoader	getSystemClassLoader	false	0				
00007-java.lang.ClassLoader-00002-00007			java.lang.ClassLoader	public	java.util.stream.Stream<java.net.URL>	resources	false	1			java.lang.String	arg0
00008-java.lang.ClassLoader-00002-00008			java.lang.ClassLoader	public static	java.lang.ClassLoader	getPlatformClassLoader	false	0				
00009-java.lang.ClassLoader-00002-00009			java.lang.ClassLoader	public final	java.lang.ClassLoader	getParent	false	0				
00010-java.lang.ClassLoader-00002-00010			java.lang.ClassLoader	public static	java.util.Enumeration<java.net.URL>	getSystemResources	false	1			java.lang.String	arg0
00011-java.lang.ClassLoader-00002-00011			java.lang.ClassLoader	public	java.io.InputStream	getResourceAsStream	false	1			java.lang.String	arg0
00012-java.lang.ClassLoader-00002-00012			java.lang.ClassLoader	public final	java.lang.Module	getUnnamedModule	false	0				
00013-java.lang.ClassLoader-00002-00013			java.lang.ClassLoader	public final	java.lang.Package[]	getDefinedPackages	false	0				
00014-java.lang.ClassLoader-00002-00014			java.lang.ClassLoader	public	void	clearAssertionStatus	false	0				
00015-java.lang.ClassLoader-00002-00015			java.lang.ClassLoader	public final	java.lang.Package	getDefinedPackage	false	1			java.lang.String	arg0
00016-java.lang.ClassLoader-00002-00016			java.lang.ClassLoader	public	void	setClassAssertionStatus	false	2			java.lang.String,boolean	arg0,arg1
00017-java.lang.ClassLoader-00002-00017			java.lang.ClassLoader	public native	int	hashCode	false	0				
00018-java.lang.ClassLoader-00002-00018			java.lang.ClassLoader	public	java.util.Enumeration<java.net.URL>	getResources	false	1			java.lang.String	arg0
00019-java.lang.ClassLoader-00002-00019			java.lang.ClassLoader	public final	boolean	isRegisteredAsParallelCapable	false	0				
00020-java.lang.ClassLoader-00002-00020			java.lang.ClassLoader	public	void	setDefaultAssertionStatus	false	1			boolean	arg0
00021-java.lang.ClassLoader-00002-00021			java.lang.ClassLoader	public final native	void	notify	false	0				
00022-java.lang.ClassLoader-00002-00022			java.lang.ClassLoader	public	void	setPackageAssertionStatus	false	2			java.lang.String,boolean	arg0,arg1
00023-java.lang.ClassLoader-00002-00023			java.lang.ClassLoader	public	java.lang.String	getName	false	0				
00024-java.lang.ClassLoader-00002-00024			java.lang.ClassLoader	public final native	void	wait	false	1			long	arg0
00025-java.lang.ClassLoader-00002-00025			java.lang.ClassLoader	public final	void	wait	false	2			long,int	arg0,arg1
00026-java.lang.ClassLoader-00002-00026			java.lang.ClassLoader	public final	void	wait	false	0				
00027-java.lang.ClassLoader-00002-00027			java.lang.ClassLoader	public	java.net.URL	getResource	false	1			java.lang.String	arg0
00028-java.lang.ClassLoader-00002-00028			java.lang.ClassLoader	public final native	java.lang.Class<?>	getClass	false	0				
00029-java.lang.ClassLoader-00002-00029			java.lang.ClassLoader	public final native	void	notifyAll	false	0				
00030-java.lang.Thread-00001-00001	java.lang.Thread	NORM_PRIORITY	java.lang.Thread	public final	boolean	isDaemon	false	0				
00031-java.lang.Thread-00001-00002	java.lang.Thread	MAX_PRIORITY	java.lang.Thread	public	java.lang.StackTraceElement[]	getStackTrace	false	0				
00032-java.lang.Thread-00001-00003	java.lang.Thread	MIN_PRIORITY	java.lang.Thread	public final	void	join	false	0				
00033-java.lang.Thread-00001-00004			java.lang.Thread	public final synchronized	void	join	false	2			long,int	arg0,arg1
00034-java.lang.Thread-00001-00005			java.lang.Thread	public final synchronized	void	join	false	1			long	arg0
00035-java.lang.Thread-00001-00006			java.lang.Thread	public	boolean	equals	false	1			java.lang.Object	arg0
00036-java.lang.Thread-00001-00007			java.lang.Thread	public	void	interrupt	false	0				
00037-java.lang.Thread-00001-00008			java.lang.Thread	public final	java.lang.String	getName	false	0				
00038-java.lang.Thread-00001-00009			java.lang.Thread	public	void	setContextClassLoader	false	1			java.lang.ClassLoader	arg0
00039-java.lang.Thread-00001-00010			java.lang.Thread	public	void	run	false	0				
00040-java.lang.Thread-00001-00011			java.lang.Thread	public static	void	onSpinWait	false	0				
00041-java.lang.Thread-00001-00012			java.lang.Thread	public	java.lang.ClassLoader	getContextClassLoader	false	0				
00042-java.lang.Thread-00001-00013			java.lang.Thread	public final	void	stop	false	0				
00043-java.lang.Thread-00001-00014			java.lang.Thread	public final native	boolean	isAlive	false	0				
00044-java.lang.Thread-00001-00015			java.lang.Thread	public static	java.lang.Thread$UncaughtExceptionHandler	getDefaultUncaughtExceptionHandler	false	0			
00045-java.lang.Thread-00001-00016			java.lang.Thread	public final	void	setPriority	false	1			int	arg0
00046-java.lang.Thread-00001-00017			java.lang.Thread	public native	int	hashCode	false	0				
00047-java.lang.Thread-00001-00018			java.lang.Thread	public static	void	setDefaultUncaughtExceptionHandler	false	1			java.lang.Thread$UncaughtExceptionHandler	arg0
00048-java.lang.Thread-00001-00019			java.lang.Thread	public	void	setUncaughtExceptionHandler	false	1			java.lang.Thread$UncaughtExceptionHandler	arg0
00049-java.lang.Thread-00001-00020			java.lang.Thread	public final native	void	notify	false	0				
00050-java.lang.Thread-00001-00021			java.lang.Thread	public static native	java.lang.Thread	currentThread	false	0				
00051-java.lang.Thread-00001-00022			java.lang.Thread	public final	void	suspend	false	0				
00052-java.lang.Thread-00001-00023			java.lang.Thread	public final native	void	wait	false	1			long	arg0
00053-java.lang.Thread-00001-00024			java.lang.Thread	public final	void	wait	false	2			long,int	arg0,arg1
00054-java.lang.Thread-00001-00025			java.lang.Thread	public final	void	wait	false	0				
00055-java.lang.Thread-00001-00026			java.lang.Thread	public final	void	checkAccess	false	0				
00056-java.lang.Thread-00001-00027			java.lang.Thread	public static	boolean	interrupted	false	0				
00057-java.lang.Thread-00001-00028			java.lang.Thread	public final native	void	notifyAll	false	0				
00058-java.lang.Thread-00001-00029			java.lang.Thread	public static native	void	yield	false	0				
00059-java.lang.Thread-00001-00030			java.lang.Thread	public static	void	sleep	false	2			long,int	arg0,arg1
00060-java.lang.Thread-00001-00031			java.lang.Thread	public static native	void	sleep	false	1			long	arg0
00061-java.lang.Thread-00001-00032			java.lang.Thread	public static	int	enumerate	false	1			java.lang.Thread[]	arg0
00062-java.lang.Thread-00001-00033			java.lang.Thread	public static	java.util.Map<java.lang.Thread, java.lang.StackTraceElement[]>	getAllStackTraces	false	0			
00063-java.lang.Thread-00001-00034			java.lang.Thread	public final	java.lang.ThreadGroup	getThreadGroup	false	0				
00064-java.lang.Thread-00001-00035			java.lang.Thread	public final synchronized	void	setName	false	1			java.lang.String	arg0
00065-java.lang.Thread-00001-00036			java.lang.Thread	public	long	getId	false	0				
00066-java.lang.Thread-00001-00037			java.lang.Thread	public	java.lang.Thread$State	getState	false	0				
00067-java.lang.Thread-00001-00038			java.lang.Thread	public static native	boolean	holdsLock	false	1			java.lang.Object	arg0
00068-java.lang.Thread-00001-00039			java.lang.Thread	public final	void	setDaemon	false	1			boolean	arg0
00069-java.lang.Thread-00001-00040			java.lang.Thread	public	java.lang.String	toString	false	0				
00070-java.lang.Thread-00001-00041			java.lang.Thread	public native	int	countStackFrames	false	0				
00071-java.lang.Thread-00001-00042			java.lang.Thread	public final	int	getPriority	false	0				
00072-java.lang.Thread-00001-00043			java.lang.Thread	public static	void	dumpStack	false	0				
00073-java.lang.Thread-00001-00044			java.lang.Thread	public	boolean	isInterrupted	false	0				
00074-java.lang.Thread-00001-00045			java.lang.Thread	public final	void	resume	false	0				
00075-java.lang.Thread-00001-00046			java.lang.Thread	public static	int	activeCount	false	0				
00076-java.lang.Thread-00001-00047			java.lang.Thread	public	java.lang.Thread$UncaughtExceptionHandler	getUncaughtExceptionHandler	false	0				
00077-java.lang.Thread-00001-00048			java.lang.Thread	public final native	java.lang.Class<?>	getClass	false	0				
00078-java.lang.Thread-00001-00049			java.lang.Thread	public synchronized	void	start	false	0				
```

- MORE
  - 出力したファイルをgitにコミットするときれいに見れる
```
$jazdump java.lang.Thread >java.lang.Thread.tsv
$jazdump java.lang.ClassLoader >java.lang.ClassLoader.tsv
```
