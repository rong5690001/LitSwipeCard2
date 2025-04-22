package com.rong.litswipecard.cardstack.swipe

/**
 * 定义拖动约束条件的数据类
 */
class DragConstraints
/**
 * 默认构造函数，设置默认允许向上和向下拖动
 */ @JvmOverloads constructor(
    /**
     * 获取是否可以向上拖动的标志
     *
     * @return 如果允许向上拖动，返回true；否则返回false
     */
    val canDragUp: Boolean = true,
    /**
     * 获取是否可以向下拖动的标志
     *
     * @return 如果允许向下拖动，返回true；否则返回false
     */
    val canDragDown: Boolean = true
) {
    /**
     * 创建拖动约束实例
     *
     * @param canDragUp 是否允许向上拖动
     * @param canDragDown 是否允许向下拖动
     */

    /**
     * 创建一个新的约束对象，可以修改原有约束条件
     *
     * @param canDragUp 新的向上拖动设置
     * @param canDragDown 新的向下拖动设置
     * @return 新创建的DragConstraints对象
     */
    fun copy(canDragUp: Boolean, canDragDown: Boolean): DragConstraints {
        return DragConstraints(canDragUp, canDragDown)
    }

    /**
     * 比较两个DragConstraints对象是否相等
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is DragConstraints) {
            return false
        }
        val that = other
        return this.canDragUp == that.canDragUp &&
                this.canDragDown == that.canDragDown
    }

    /**
     * 计算对象的哈希码
     */
    override fun hashCode(): Int {
        var result = if (canDragUp) 1 else 0
        result = 31 * result + (if (canDragDown) 1 else 0)
        return result
    }

    /**
     * 返回对象的字符串表示
     */
    override fun toString(): String {
        return "DragConstraints(canDragUp=" + this.canDragUp +
                ", canDragDown=" + this.canDragDown + ')'
    }
}