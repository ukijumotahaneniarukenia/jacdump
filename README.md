# jacdump
javalangのクラス定義情報をクロステーブル形式で標準出力に出力するコマンド

# ダウンロード

[jacdump](https://github.com/ukijumotahaneniarukenia/jacdump/releases/tag/2-0-0)

# インストール

```
curl -fsSLO https://github.com/ukijumotahaneniarukenia/jacdump/releases/download/2-0-0/jacdump-2-0-0-SNAPSHOT.jar
```

# 実行

引数指定

```
$java -jar jacdump-2-0-0-SNAPSHOT.jar java.lang.Thread java.lang.ClassLoader
```

引数未指定

- システムライブラリに存在するすべてのクラスファイルが対象

```
$java -jar jacdump-2-0-0-SNAPSHOT.jar
```
