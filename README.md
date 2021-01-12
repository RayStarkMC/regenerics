# regenerics
再帰的ジェネリクスによるプログラムの抽象化を目指します。

## 再帰的ジェネリクス
再帰的ジェネリクスとは次のように、型変数の上限が自身と同じ型になっているジェネリック型を定義する手法です。
```java
public interface Regeneric<I extends Regeneric<I>> {}
```
一般的には再帰的ジェネリクスを用いた定義では事前契約として型引数として自身のサブタイプを指定する事とし、
自身の実装型に依存した定義を行います。同様の手法がJava標準APIのEnumやComparableで実際に採用されています。

## 安全性
事前契約に基づく再帰的ジェネリクスは型安全ではありません。次のように型変数として自身のサブタイプ以外を指定することができます。
```java
public interface A extends Regeneric<A> {}
public interface B extends Regeneric<A> {}
```
ライブラリではこのような事前契約に反する継承関係を最大限早いタイミング(理想的には再帰的ジェネリクス型のインスタンスの生成時)に検知し、
ClassCastExceptionをスローします。