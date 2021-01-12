package regenerics;

import org.jetbrains.annotations.NotNull;

/**
 * Regenericの骨格実装。
 *
 * <p>castメソッドがfinal修飾されたRegenericです。このクラスを継承する事でキャストメソッドの安全性を保証することができます。
 *
 * @param <I>実装型
 */
public abstract class RegenericClass<I extends RegenericClass<I>> implements Regeneric<I> {
    /**
     * 唯一のコンストラクタ。
     *
     * <p>このコンストラクタが呼び出されるとcastメソッドが一度実行されます。
     * 型変数に自身のサブタイプでない型を指定した場合ClassCastExceptionがスローされます。
     *
     * @throws ClassCastException Iが自身のサブタイプで無い場合
     */
    protected RegenericClass() {
        cast();
    }

    /**
     * このインスタンスを実装型にキャストします。
     *
     * <p>このメソッドは呼び出し元のインスタンスを返します。このメソッドはオーバーライドできません。
     *
     * @throws ClassCastException Iが自身のサブタイプで無い場合
     * @return キャストされたインスタンス
     */
    @Override
    @NotNull
    public final I cast() {
        return Regeneric.super.cast();
    }
}
