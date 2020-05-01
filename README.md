# jazdump
javalangのクラス定義情報をクロステーブル形式で標準出力に出力するコマンド

# もとねた
script-sketch/java/00054-java-特定のクラスに含まれる定数メソッド一覧取得/

# ダウンロード

[jazdump](https://github.com/ukijumotahaneniarukenia/jazdump/releases/tag/1-0-0)

```
unizp jazdump-1.0-SNAPSHOT-bin.zip

or

tar xvf jazdump-1.0-SNAPSHOT-bin.tar.gz

or

tar xvf jazdump-1.0-SNAPSHOT-bin.tar.bz2
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
```

- MORE
  - 出力したファイルをgitにコミットするときれいに見れる
```
$jazdump java.lang.Thread >java.lang.Thread.tsv
$jazdump java.lang.ClassLoader >java.lang.ClassLoader.tsv
```
