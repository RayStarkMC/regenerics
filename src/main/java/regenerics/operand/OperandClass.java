package regenerics.operand;

import org.jetbrains.annotations.NotNull;
import regenerics.RegenericClass;

import java.util.function.Function;

/**
 * Operandの骨格実装。
 *
 * <p>opメソッドがfinal修飾されたOperandです。
 *
 * @param <I>実装型
 */
public abstract class OperandClass<I extends OperandClass<I>> extends RegenericClass<I> implements Operand<I> {

    /**
     * 唯一のコンストラクタ。
     */
    protected OperandClass() {}

    /**
     * 外部関数に自身のインスタンスを適用して評価します。
     *
     * <p>このメソッドはoperatorを評価した結果を返します。自身の型と関数の引数の型が一致しない場合はコンパイルエラーになります。
     * OperandをGeneric型で実装し、関数の引数をパラメタライズド型にする事で、
     * Generic型の型引数が関数と一致する時のみ呼び出す事が出来るインスタンスメソッドを模倣できます。
     *
     * <p>このメソッドはオーバーライドできません。
     *
     * @param operator 自身を引数に取る関数
     * @param <R> operatorの戻り値の型
     * @return operatorを評価した結果
     */
    @Override
    @NotNull
    public final <R> R op(@NotNull Function<? super I, ? extends R> operator) {
        return Operand.super.op(operator);
    }
}
