package com.wds.weizixun;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.wds.adapter.EMMessageAdapter;
import com.wds.base.BaseActivity;
import com.wds.utils.Constants;
import com.wds.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btN_send)
    Button btNSend;
    @BindView(R.id.btn_record)
    Button btnRecord;
    @BindView(R.id.btn_send_audio)
    Button btnSendAudio;

    private EMMessageAdapter emMessageAdapter;
    private String curName;
    private String toName;
    private EMMessageListener msgListener;
    private ArrayList<EMMessage> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData() {
        super.initData();
        toName = getIntent().getStringExtra(Constants.NAME);
        curName = (String) SharedPreferencesUtils.getParam(this, Constants.NAME, "a");
        tvTitle.setText(curName + "正在和" + toName + "聊天中...");

        //
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {

                list.addAll(messages);
                emMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    protected void initView() {
        super.initView();


        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        emMessageAdapter = new EMMessageAdapter(list, this, toName, curName);
        rv.setAdapter(emMessageAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @OnClick({R.id.btN_send, R.id.btn_record, R.id.btn_send_audio})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btN_send:
                send();
                break;
            case R.id.btn_record:
                break;
            case R.id.btn_send_audio:
                break;
        }
    }

    private void send() {
        String content = etContent.getText().toString();

        if (content.isEmpty()) {
            Toast.makeText(ChatActivity.this, "请输入消息", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, toName);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(message);
                        emMessageAdapter.notifyDataSetChanged();
                        etContent.setText("");
                    }
                });
            }
        }).start();

    }
}
