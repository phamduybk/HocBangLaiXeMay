package vn.com.lvvu.hocbanglaixemay.views;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * chia khoảng cách cho grid layout
 * Created by levan on 7/18/2019.
 */

public class GridViewDivider extends RecyclerView.ItemDecoration {

    private final int spacing;

    private int numberColumn;

    public GridViewDivider(int spacing, int numberColumn) {
        this.spacing = spacing;
        this.numberColumn = numberColumn;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);

        final int totalSpanCount = getTotalSpanCount(parent);
        int spanSize = getItemSpanSize(parent, position);
        if (totalSpanCount == spanSize) {
            return;
        }

        outRect.top = isInTheFirstRow(position, totalSpanCount) ? 0 : spacing;
        outRect.left = isFirstInRow(position, totalSpanCount) ? 0 : spacing / numberColumn;
        outRect.right = isLastInRow(position, totalSpanCount) ? 0 : spacing / numberColumn;
        outRect.bottom = 0; // don't need
    }

    private boolean isInTheFirstRow(int position, int spanCount) {
        return position < spanCount;
    }

    private boolean isFirstInRow(int position, int spanCount) {
        return position % spanCount == 0;
    }

    private boolean isLastInRow(int position, int spanCount) {
        return isFirstInRow(position + 1, spanCount);
    }

    private int getTotalSpanCount(RecyclerView parent) {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        return layoutManager instanceof GridLayoutManager ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
    }

    private int getItemSpanSize(RecyclerView parent, int position) {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        return layoutManager instanceof GridLayoutManager
                ? ((GridLayoutManager) layoutManager).getSpanSizeLookup().getSpanSize(position)
                : 1;
    }
}
