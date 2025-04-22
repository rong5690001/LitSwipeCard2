//package com.rong.litswipecard.cardstack.view
//
//import android.content.Context
//import android.util.AttributeSet
//import com.rong.litswipecard.cardstack.cardgrid.view.BaseCardCollectionLayout
//import kotlin.jvm.internal.SourceDebugExtension
//import kotlin.math.abs
//
///**
// * 卡片网格布局
// * 以网格形式展示卡片的布局控件
// */
//@Metadata(
//    d1 = ["\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 @2\u00020\u0001:\u0004@ABCB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\u000cH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\u00112\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0011¢\u0006\u0004\b\u001b\u0010\u001aJ\u0015\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\"\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0014H\u0014¢\u0006\u0004\b\"\u0010\u0018J\u0017\u0010$\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0014H\u0016¢\u0006\u0004\b$\u0010%R$\u0010-\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0016\u00100\u001a\u00020\u000c8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00104\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00108\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u0010<\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010?\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>¨\u0006D"],
//    d2 = ["Lcom/tinder/cardstack/view/CardGridLayout;", "Lcom/tinder/cardstack/cardgrid/view/BaseCardCollectionLayout;", "Landroid/content/Context;", "ctx", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "getScrollProgressPercent", "()F", "Landroidx/recyclerview/widget/GridLayoutManager;", "getLayoutManager", "()Landroidx/recyclerview/widget/GridLayoutManager;", "Landroidx/recyclerview/widget/RecyclerView\$Adapter;", "adapter", "", "setAdapter", "(Landroidx/recyclerview/widget/RecyclerView\$Adapter;)V", "", "spanCount", "headerOffset", "setSpanCount", "(II)V", "showLoadingMore", "()V", "hideLoading", "Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;", "factory", "setLoadingStatusViewHolderFactory", "(Lcom/tinder/cardstack/cardgrid/view/LoadingStatusViewHolderFactory;)V", "startIndex", "numberOfItemsInserted", "checkIfNewInsertsAffectFrozenCards", "position", "smoothScrollToPosition", "(I)V", "Lcom/tinder/cardstack/view/CardGridLayout\$OnScrollProgressListener;", "Z1", "Lcom/tinder/cardstack/view/CardGridLayout\$OnScrollProgressListener;", "getOnScrollProgressListener", "()Lcom/tinder/cardstack/view/CardGridLayout\$OnScrollProgressListener;", "setOnScrollProgressListener", "(Lcom/tinder/cardstack/view/CardGridLayout\$OnScrollProgressListener;)V", "onScrollProgressListener", "a2", "Landroidx/recyclerview/widget/GridLayoutManager;", "gridLayoutManager", "Landroidx/recyclerview/widget/RecyclerView\$ItemDecoration;", "b2", "Landroidx/recyclerview/widget/RecyclerView\$ItemDecoration;", "gridSpacingItemDecoration", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter;", "c2", "Lcom/tinder/cardstack/cardgrid/view/CardGridViewHolderAdapter;", "cardGridViewHolderAdapter", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker;", "d2", "Lcom/tinder/cardstack/cardgrid/view/ScrollVelocityTracker;", "velocityTracker", "e2", "I", "itemSpacingPx", "Companion", "OnScrollProgressListener", "a", "b", "cardstack_release"],
//    k = 1,
//    mv = [1, 8, 0],
//    xi = 48
//)
//@SourceDebugExtension(["SMAP\nCardGridLayout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardGridLayout.kt\ncom/tinder/cardstack/view/CardGridLayout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,129:1\n1#2:130\n*E\n"]) /* loaded from: classes7.dex */
//class CardGridLayout(ctx: Context, attributeSet: AttributeSet?) : BaseCardCollectionLayout(ctx, attributeSet) {
//    /**
//     * 获取滚动进度监听器
//     *
//     * @return 滚动进度监听器
//     */
//    /**
//     * 设置滚动进度监听器
//     *
//     * @param onScrollProgressListener 滚动进度监听器
//     */
//    /**
//     * 滚动进度监听器
//     */
//    var onScrollProgressListener: OnScrollProgressListener? = null
//
//    /**
//     * 网格布局管理器
//     */
//    private var gridLayoutManager: GridLayoutManager? = null
//
//    /**
//     * 网格间距装饰器
//     */
//    private var gridSpacingItemDecoration: ItemDecoration? = null
//
//    /**
//     * 卡片网格视图持有者适配器
//     */
//    private var cardGridViewHolderAdapter: CardGridViewHolderAdapter
//
//    /**
//     * 滚动速度跟踪器
//     */
//    private val velocityTracker: ScrollVelocityTracker
//
//    /**
//     * 项目间距(像素)
//     */
//    private val itemSpacingPx: Int
//
//    /**
//     * 滚动进度监听器接口
//     */
//    interface OnScrollProgressListener {
//        /**
//         * 当滚动时调用
//         *
//         * @param progress 滚动进度(0-1)
//         * @param velocity 滚动速度
//         */
//        fun onScrolled(progress: Float, velocity: Float)
//    }
//
//    /**
//     * 滚动监听器实现
//     */
//    private inner class ScrollListener : RecyclerView.OnScrollListener() {
//        /**
//         * 检查是否可以更新
//         *
//         * @return 是否可以更新
//         */
//        fun canUpdate(): Boolean {
//            return (UPDATE_INTERVAL_MS.toLong()) < System.currentTimeMillis() - velocityTracker.getLastUpdateTimestamp()
//        }
//
//        /**
//         * 当滚动时调用
//         *
//         * @param recyclerView 滚动的RecyclerView
//         * @param dx X方向滚动量
//         * @param dy Y方向滚动量
//         */
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView")
//            if (canUpdate()) {
//                val scrollProgressPercent = this@CardGridLayout.scrollProgressPercent
//                velocityTracker.track(scrollProgressPercent)
//                val onScrollProgressListener = this@CardGridLayout.onScrollProgressListener
//                onScrollProgressListener?.onScrolled(scrollProgressPercent, velocityTracker.getVelocity())
//            }
//        }
//    }
//
//    /**
//     * 跨度大小查找器
//     */
//    private inner class SpanSizeLookup : GridLayoutManager.SpanSizeLookup() {
//        /**
//         * 获取指定位置的跨度大小
//         *
//         * @param position 项目位置
//         * @return 跨度大小
//         */
//        override fun getSpanSize(position: Int): Int {
//            if (position != cardGridViewHolderAdapter.findLoadingStatusPosition()) {
//                return 1
//            }
//            var gridLayoutManager: GridLayoutManager? = this@CardGridLayout.gridLayoutManager
//            if (gridLayoutManager == null) {
//                Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager")
//                gridLayoutManager = null
//            }
//            return gridLayoutManager.getSpanCount()
//        }
//    }
//
//    /**
//     * 构造函数
//     *
//     * @param ctx 上下文
//     */
//    constructor(ctx: Context) : this(ctx, null) {
//        Intrinsics.checkNotNullParameter(ctx, "ctx")
//    }
//
//    private val scrollProgressPercent: Float
//        /**
//         * 获取滚动进度百分比
//         *
//         * @return 滚动进度百分比(0-1)
//         */
//        get() = computeVerticalScrollOffset() / computeVerticalScrollRange()
//
//    /**
//     * 检查新插入是否影响已冻结的卡片
//     *
//     * @param startIndex 开始插入的索引
//     * @param numberOfItemsInserted 插入的项目数量
//     */
//    protected override fun checkIfNewInsertsAffectFrozenCards(startIndex: Int, numberOfItemsInserted: Int) {
//        // 网格布局不需要检查冻结卡片
//    }
//
//    /**
//     * 隐藏加载状态
//     */
//    fun hideLoading() {
//        cardGridViewHolderAdapter.removeLoadingStatus()
//    }
//
//    /**
//     * 设置适配器
//     *
//     * @param adapter 适配器
//     */
//    override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
//        Intrinsics.checkNotNullParameter(adapter, "adapter")
//        super.setAdapter(adapter)
//        this.cardGridViewHolderAdapter = adapter as CardGridViewHolderAdapter
//    }
//
//    /**
//     * 设置加载状态视图持有者工厂
//     *
//     * @param factory 加载状态视图持有者工厂
//     */
//    fun setLoadingStatusViewHolderFactory(factory: LoadingStatusViewHolderFactory) {
//        Intrinsics.checkNotNullParameter(factory, "factory")
//        cardGridViewHolderAdapter.setLoadingStatusViewHolderFactory(factory)
//    }
//
//    /**
//     * 设置列数
//     *
//     * @param spanCount 列数
//     * @param headerOffset 标题偏移量
//     */
//    fun setSpanCount(spanCount: Int, headerOffset: Int) {
//        val cardGridLayoutManager: CardGridLayoutManager = CardGridLayoutManager(getContext(), spanCount)
//        this.gridLayoutManager = cardGridLayoutManager
//        cardGridLayoutManager.setSpanSizeLookup(SpanSizeLookup())
//        var gridLayoutManager: GridLayoutManager? = this.gridLayoutManager
//        if (gridLayoutManager == null) {
//            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager")
//            gridLayoutManager = null
//        }
//        setLayoutManager(gridLayoutManager)
//        val itemDecoration: ItemDecoration? = this.gridSpacingItemDecoration
//        if (itemDecoration != null) {
//            removeItemDecoration(itemDecoration)
//        }
//        val gridSpacingItemDecoration: GridSpacingItemDecoration = GridSpacingItemDecoration(spanCount, this.itemSpacingPx, headerOffset)
//        this.gridSpacingItemDecoration = gridSpacingItemDecoration
//        addItemDecoration(gridSpacingItemDecoration)
//    }
//
//    /**
//     * 显示加载更多
//     */
//    fun showLoadingMore() {
//        cardGridViewHolderAdapter.appendLoadingStatus()
//    }
//
//    /**
//     * 平滑滚动到指定位置
//     *
//     * @param position 目标位置
//     */
//    override fun smoothScrollToPosition(position: Int) {
//        val currentPosition = (scrollProgressPercent * getAdapter().getItemCount()) as Int
//        val distance = abs((position - currentPosition).toDouble()).toInt()
//        val threshold = SCROLL_THRESHOLD
//        if (distance > threshold) {
//            scrollToPosition(if (currentPosition < position) position - threshold else position + threshold)
//        }
//        super.smoothScrollToPosition(position)
//    }
//
//    /**
//     * 构造函数
//     *
//     * @param ctx 上下文
//     * @param attributeSet 属性集
//     */
//    init {
//        Intrinsics.checkNotNullParameter(ctx, "ctx")
//        this.cardGridViewHolderAdapter = CardGridViewHolderAdapter()
//        this.velocityTracker = ScrollVelocityTracker()
//        setAdapter(this.cardGridViewHolderAdapter)
//        this.itemSpacingPx = TypedValue.applyDimension(1, DEFAULT_SPACING_DP, getContext().getResources().getDisplayMetrics()).toInt()
//        `setSpanCount$default`(this, DEFAULT_SPAN_COUNT, 0, 2, null)
//        addOnScrollListener(ScrollListener())
//    }
//
//    val layoutManager: GridLayoutManager
//        /**
//         * 获取布局管理器
//         *
//         * @return 网格布局管理器
//         */
//        get() {
//            val gridLayoutManager: GridLayoutManager? = this.gridLayoutManager
//            if (gridLayoutManager != null) {
//                return gridLayoutManager
//            }
//            Intrinsics.throwUninitializedPropertyAccessException("gridLayoutManager")
//            return null
//        }
//
//    companion object {
//        /**
//         * 默认列数
//         */
//        private const val DEFAULT_SPAN_COUNT = 2
//
//        /**
//         * 默认间距(dp)
//         */
//        private const val DEFAULT_SPACING_DP = 8.0f
//
//        /**
//         * 更新间隔时间(毫秒)
//         */
//        private const val UPDATE_INTERVAL_MS = 100
//
//        /**
//         * 滚动阈值(项目数)
//         */
//        private const val SCROLL_THRESHOLD = 10
//
//        /**
//         * 设置列数(带默认参数)
//         *
//         * @param spanCount 列数
//         * @param headerOffset 标题偏移量
//         */
//        fun `setSpanCount$default`(cardGridLayout: CardGridLayout, spanCount: Int, headerOffset: Int, defaultMask: Int, unused: Any?) {
//            var headerOffset = headerOffset
//            if ((defaultMask and 2) != 0) {
//                headerOffset = 0
//            }
//            cardGridLayout.setSpanCount(spanCount, headerOffset)
//        }
//    }
//}