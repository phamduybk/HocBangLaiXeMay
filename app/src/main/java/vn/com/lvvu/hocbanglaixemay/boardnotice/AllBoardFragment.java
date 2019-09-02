package vn.com.lvvu.hocbanglaixemay.boardnotice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.base.BaseFragment;

/**
 * Danh sách các loại biển báo giao thông
 * <p>
 * Created by levan on 7/21/2019.
 */

public class AllBoardFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_prohibit_board)
    TextView tvProhibitBoard;

    @BindView(R.id.tv_dangerous_board)
    TextView tvDangerousBoard;

    @BindView(R.id.tv_instruction_board)
    TextView tvInstructionBoard;

    @BindView(R.id.tv_command_board)
    TextView tvCommandBoard;

    @BindView(R.id.tv_sub_board)
    TextView tvSubBoard;

    @BindView(R.id.tv_line_road)
    TextView tvLineBoard;

    @BindView(R.id.banner)
    AdView banner;

    public static AllBoardFragment newInstance() {

        Bundle args = new Bundle();

        AllBoardFragment fragment = new AllBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {
        try {
            tvProhibitBoard.setOnClickListener(this);
            tvDangerousBoard.setOnClickListener(this);
            tvInstructionBoard.setOnClickListener(this);
            tvCommandBoard.setOnClickListener(this);
            tvLineBoard.setOnClickListener(this);
            tvSubBoard.setOnClickListener(this);

            Common.requestAds(banner);
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_all_board;
    }

    @Override
    public void onClick(View view) {
        try {
            Common.enableView(view);
            switch (view.getId()) {
                case R.id.tv_prohibit_board:
                    openProhibitBoardActivity();
                    break;

                case R.id.tv_dangerous_board:
                    openDangerousBoardActivity();
                    break;

                case R.id.tv_instruction_board:
                    openInstructionActivity();
                    break;

                case R.id.tv_command_board:
                    openCommandBoardActivity();
                    break;

                case R.id.tv_sub_board:
                    openSubBoardActivity();
                    break;

                case R.id.tv_line_road:
                    openLineRoadsBoardActivity();
                    break;

            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Mở màn hình biển báo cấm
     *
     * @create by le van vu
     */
    private void openProhibitBoardActivity() {

        Intent intent = new Intent(getActivity(), ProhibitBoardActivity.class);
        startActivity(intent);

    }

    /**
     * Mở màn hình biển báo nguy hiểm
     *
     * @create by le van vu
     */
    private void openDangerousBoardActivity() {

        Intent intent = new Intent(getActivity(), DangerousBoardActivity.class);
        startActivity(intent);

    }

    /**
     * Mở màn hình biển báo hiệu lệnh
     *
     * @create by le van vu
     */
    private void openCommandBoardActivity() {

        Intent intent = new Intent(getActivity(), CommandBoardActivity.class);
        startActivity(intent);

    }

    /**
     * Mở màn hình biển báo hướng dẫn
     *
     * @create by le van vu
     */
    private void openInstructionActivity() {

        Intent intent = new Intent(getActivity(), InstructionBoardActivity.class);
        startActivity(intent);

    }

    /**
     * Mở màn hình biển báo phụ
     *
     * @create by le van vu
     */
    private void openSubBoardActivity() {

        Intent intent = new Intent(getActivity(), SubBoardActivity.class);
        startActivity(intent);

    }

    /**
     * Mở màn hình Vạch kẻ đường
     *
     * @create by le van vu
     */
    private void openLineRoadsBoardActivity() {

        Intent intent = new Intent(getActivity(), LineRoadActivity.class);
        startActivity(intent);

    }

}
