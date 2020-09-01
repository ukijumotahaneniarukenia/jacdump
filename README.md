# jacdump
javalangのクラス定義情報をクロステーブル形式で標準出力に出力するコマンド

インストール

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/jacdump/releases/download/2-0-0/jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar
```

使い方

```
$ java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar 
Usageだよーん

java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar --method

java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar --list-up

java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar --method java.lang.ClassLoader java.lang.Thread

java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar --constant

java -jar jacdump-2-0-0-SNAPSHOT-jar-with-dependencies.jar --constant java.lang.ClassLoader java.lang.Thread

```
