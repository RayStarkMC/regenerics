package regenerics.operand;

import org.jetbrains.annotations.NotNull;
import regenerics.Regeneric;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 演算のオペランド
 *
 * <p>自身を引数に取る関数をあたかもインスタンスメソッドであるかのように呼び出すopメソッドを定義します。
 * また、多変数関数をoperatorとして使うためのユーティリティメソッドを定義します。
 *
 * @param <I> 実装型
 * @see Regeneric
 */
public interface Operand<I extends Operand<I>> extends Regeneric<I> {

    /**
     * 外部関数に自身のインスタンスを適用して評価します。
     *
     * <p>このメソッドはoperatorを評価した結果を返します。自身の型と関数の引数の型が一致しない場合はコンパイルエラーになります。
     * OperandをGeneric型で実装し、関数の引数をパラメタライズド型にする事で、
     * Generic型の型引数が関数と一致する時のみ呼び出す事が出来るインスタンスメソッドを模倣できます。
     *
     * <p>このメソッドをオーバーライドすべきではありません。
     * 実装クラスではクラス自体をfinalで修飾するか、このメソッドをfinalに修飾すべきです。
     *
     * @param operator 自身を引数に取る関数
     * @param <R> operatorの戻り値の型
     * @return operatorを評価した結果
     */
    @NotNull
    default <R> R op(@NotNull Function<? super I, ? extends R> operator) {
        return operator.apply(cast());
    }

    /**
     * 多変数関数と第二以降の引数からオペランドを待機する関数を作ります。
     *
     * <p>このメソッドは外部メソッドとして定義された関数をopの引数として使えるように加工します。
     *
     * @param f2 二変数関数
     * @param t2 第二引数
     * @param <I> オペランド
     * @param <T2> 第二引数の型
     * @param <R> 戻り値の型
     * @return オペランドを待機する関数
     */
    @NotNull
    static <I extends Operand<I>, T2, R> Function<I, R> operation(@NotNull BiFunction<? super I, ? super T2, ? extends R> f2, @NotNull T2 t2) {
        return t1 -> f2.apply(t1, t2);
    }
}
