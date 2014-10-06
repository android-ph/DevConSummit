package ph.devcon.android.news;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ph.devcon.android.R;
import ph.devcon.android.news.adapter.NewsAdapter;
import ph.devcon.android.news.db.News;

/**
 * Created by lope on 10/6/14.
 */
public class NewsFragment extends Fragment {

    @InjectView(R.id.lvw_news)
    ListView lvwNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.inject(this, rootView);
        lvwNews.setAdapter(new NewsAdapter(getActivity(), new ArrayList<News>()));
        lvwNews.addFooterView(buildFooterView(inflater));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected View buildFooterView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.footer_standard, null);
    }
}