package regenerics;

import org.jetbrains.annotations.NotNull;

/**
 * 再帰的ジェネリクス型。
 *
 * <p>再帰的ジェネリクス型による抽象化の基底インターフェース。
 * 自身の実装型を型変数として保持しており、インスタンスをその実装型にキャストすることができます。
 * サブタイプでは実装型に依存した定義を行うことができます。
 *
 * <p>実装型を定義する場合は型変数に正しく実装型を指定する必要があります。
 *
 * @param <I>このインターフェースの実装型
 */
public interface Regeneric<I extends Regeneric<I>> {

    /**
     * このインスタンスを実装型にキャストします。
     *
     * <p>このメソッドは呼び出し元のインスタンスを返します。
     *
     * <p>このメソッドをオーバーライドすべきではありません。
     * 実装クラスではクラス自体をfinalで修飾するか、このメソッドをfinalに修飾すべきです。
     *
     * @throws ClassCastException Iが自身のサブタイプで無い場合
     * @return このインスタンス
     */
    @SuppressWarnings("unchecked")
    @NotNull
    default I cast() {
        return (I)this;
    }
}
