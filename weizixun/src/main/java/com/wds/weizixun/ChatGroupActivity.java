package com.wds.weizixun;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.wds.adapter.EMMessageAdapter;
import com.wds.base.BaseActivity;
import com.wds.utils.Constants;
import com.wds.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatGroupActivity extends BaseActivity {
    @BindView(R.id.btn_create_group)
    Button btnCreateGroup;
    @BindView(R.id.et_group_id)
    EditText etGroupId;
    @BindView(R.id.btn_join_group)
    Button btnJoinGroup;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_group_bto)
    EditText etGroupBto;
    @BindView(R.id.btn_join_bto)
    Button btnJoinBto;
    private String groupID;
    private EMMessageAdapter emMessageAdapter;
    private ArrayList<EMMessage> list;
    private String curName;
    private EMMessageListener msgListener;

    @Override
    protected int getLayout() {
        return R.layout.activity_chat_group;
    }

    @Override
    protected void initView() {
        super.initView();
        curName = (String) SharedPreferencesUtils.getParam(this, Constants.CURMAME, "a");
        list = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        emMessageAdapter = new EMMessageAdapter(list, this, "", curName);
        rv.setAdapter(emMessageAdapter);
    }

    @Override
    protected void initReceiver() {
        super.initReceiver();
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
            //收到消息，任何消息都可以接受，需要过滤，只处理这个群的消息
                if (messages!=null&&messages.size()>0) {
                    ArrayList<EMMessage> arrayList = new ArrayList<>();
                    for (int i = 0; i < messages.size(); i++) {
                        EMMessage emMessage = messages.get(i);
                        String to = emMessage.getTo();
                        if (!TextUtils.isEmpty(groupID)&&groupID.equals(to)){
                        arrayList.add(emMessage);
                        }
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(messages);
                        emMessageAdapter.notifyDataSetChanged();
                    }
                });

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
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @OnClick({R.id.btn_create_group, R.id.btn_join_group, R.id.btn_join_bto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_group:
                createGroup();
                break;
            case R.id.btn_join_group:
                joinGroup();
                break;
            case R.id.btn_join_bto:
                send();
                break;
        }
    }
    //发送消息
    private void send() {
        String content = etGroupBto.getText().toString().trim();
        if (content.isEmpty()){
            Toast.makeText(this, "消息发送不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, groupID);
                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.GroupChat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.add(message);
                        emMessageAdapter.notifyDataSetChanged();
                        etGroupBto.setText("");
                    }
                });
            }
        }).start();
    }
    //加入群
    private void joinGroup() {
        groupID = etGroupId.getText().toString().trim();
        if (groupID.isEmpty()){
            Toast.makeText(this, "群号不能为null", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().joinGroup(groupID);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ChatGroupActivity.this, "加入群成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ChatGroupActivity.this, "加入群失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //创建群
    private void createGroup() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 创建群组
                 * @param groupName 群组名称
                 * @param desc 群组简介
                 * @param allMembers 群组初始成员，如果只有自己传空数组即可
                 * @param reason 邀请成员加入的reason
                 * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
                 *               option.inviteNeedConfirm表示邀请对方进群是否需要对方同意，默认是需要用户同意才能加群的。
                 *               option.extField创建群时可以为群组设定扩展字段，方便个性化订制。
                 * @return 创建好的group
                 * @throws HyphenateException
                 */
                EMGroupOptions option = new EMGroupOptions();
                option.maxUsers = 200;
                option.style = EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite;

                try {
                    EMGroup group = EMClient.getInstance().groupManager().createGroup("聊天群", "聊天", new String[]{"a", "b"}, "ssss", option);
                    String groupId = group.getGroupId();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            etGroupId.setText(groupId);
                            Toast.makeText(ChatGroupActivity.this, "创建成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChatGroupActivity.this, "创建失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
