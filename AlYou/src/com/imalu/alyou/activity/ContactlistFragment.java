/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imalu.alyou.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.easemob.chat.EMContactManager;
import com.imalu.alyou.R;
import com.easemob.exceptions.EaseMobException;
import com.imalu.alyou.Constant;
import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.adapter.ContactAdapter;
import com.imalu.alyou.db.InviteMessgeDao;
import com.imalu.alyou.domain.HXUser;
import com.imalu.alyou.domain.User;
import com.imalu.alyou.widget.Sidebar;

/**
 * 联系人列表页
 * 
 */
public class ContactlistFragment extends Fragment {
	private ContactAdapter adapter;
	private List<HXUser> contactList;
	private ListView listView;
	private boolean hidden;
	private Sidebar sidebar;
	private InputMethodManager inputMethodManager;
	private List<String> blackList;


	protected static final String TAG = "ContractFragment";

	private Button[] mTabs;
	private ImageView[] mImages;
	private ImageView imageView;
	private FriendlistFragment friendListFragment;//好友列表
	private ConcernlistFragment concernlistFragment;//关注界面
	private ConsortialistFragment consortialistFragment;//公会界面
	private FanslistFragment fanslistFragment;//粉丝界面

	private Fragment[] fragments;
	private int index;
	private RelativeLayout[] tab_containers;
	// 当前fragment的index
	private int currentTabIndex;
	//private NewMessageBroadcastReceiver msgReceiver;
	// 账号在别处登录
	public boolean isConflict = false;
	private int[] location;
	private int[] locationBt;
	//动画时间
	private static int TIME=500;
private RelativeLayout relativeLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_contact_list, container, false);
	}

	private void initView() {
		mTabs = new Button[4];
		mTabs[0] = (Button) this.getView().findViewById(R.id.btn_friend_list);
		mTabs[1] = (Button) this.getView().findViewById(R.id.btn_club_list);
		mTabs[2] = (Button) this.getView().findViewById(R.id.btn_fans_list);
		mTabs[3] = (Button) this.getView().findViewById(R.id.btn_tweet_list);
		
		mImages=new ImageView[4];
		mImages[0]=(ImageView) this.getView().findViewById(R.id.image_guangbiao0);
		relativeLayout=(RelativeLayout) this.getView().findViewById(R.id.image_layout);
		

		/*mImages[1]=(ImageView) this.getView().findViewById(R.id.image_guangbiao1);
		mImages[2]=(ImageView) this.getView().findViewById(R.id.image_guangbiao2);
		mImages[3]=(ImageView) this.getView().findViewById(R.id.image_guangbiao3);*/
		//imageView=(ImageView) this.getView().findViewById(R.id.contact_image);


		// 把第一个tab设为选中状态
		mTabs[0].setSelected(true);
		mTabs[0].setTextColor(Color.GREEN);
		mImages[0].setVisibility(View.VISIBLE);
		//	imageView.bringToFront();

		location=new int[2];
		locationBt=new int[2];
		relativeLayout.getLocationOnScreen(location);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//防止被T后，没点确定按钮然后按了home键，长期在后台又进app导致的crash
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
			return;
		super.onCreate(savedInstanceState);

		initView();

		friendListFragment = new FriendlistFragment();
		consortialistFragment= new ConsortialistFragment();
		concernlistFragment= new ConcernlistFragment();
		fanslistFragment= new FanslistFragment();
		fragments = new Fragment[] { friendListFragment ,consortialistFragment, concernlistFragment, fanslistFragment };
		// 添加显示第一个fragment
		FragmentTransaction	transaction=getChildFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_layout, friendListFragment)
		.add(R.id.fragment_layout, consortialistFragment)
		.add(R.id.fragment_layout, concernlistFragment)
		.add(R.id.fragment_layout, fanslistFragment)
		.hide(consortialistFragment)
		.hide(concernlistFragment)
		.hide(fanslistFragment)
		.show(friendListFragment)
		.commit();
		onContractTabClicked();
	}

	public void onContractTabClicked() {
		mTabs[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mTabs[0].getLocationOnScreen(locationBt);
				index=0;
				mTabs[0].setTextColor(Color.GREEN);
				mTabs[1].setTextColor(Color.WHITE);
				mTabs[2].setTextColor(Color.WHITE);
				mTabs[3].setTextColor(Color.WHITE);

				//	mImages[0].setVisibility(View.VISIBLE);
				//	mImages[1].setVisibility(View.GONE);
				//	mImages[2].setVisibility(View.GONE);
				//	mImages[3].setVisibility(View.GONE);
				
				/*	mImages[0].bringToFront();
				mImages[1].bringToFront();
				mImages[2].bringToFront();
				mImages[3].bringToFront();*/
				//	imageView.bringToFront();
				mTabs[0].getLocationOnScreen(locationBt);
				Log.e("坐标", location[0]+"");
				Animation translateAnimation = new TranslateAnimation(location[0],locationBt[0],0,0);  
				translateAnimation.setDuration(TIME); 
				translateAnimation.setFillAfter(true);
				translateAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						Log.e("开始坐标1：",location[0]+" "+locationBt[0]);
					}
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
//						mImages[0].clearAnimation();  
//						mImages[0].layout(mTabs[0].getLeft(), 41,0,51);
//						Log.e("坐标1：",mImages[0].getLeft()+" "+mImages[0].getTop()+" "+mImages[0].getRight()+" "+mImages[0].getBottom());
						location[0]=locationBt[0];
					}
				});

				relativeLayout.startAnimation(translateAnimation);
				util(index);
			}
		});
		mTabs[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index=1;
				mTabs[0].setTextColor(Color.WHITE);
				mTabs[1].setTextColor(Color.GREEN);
				mTabs[2].setTextColor(Color.WHITE);
				mTabs[3].setTextColor(Color.WHITE);

				//	mImages[0].setVisibility(View.GONE);
				//	mImages[1].setVisibility(View.VISIBLE);
				//	mImages[2].setVisibility(View.GONE);
				//	mImages[3].setVisibility(View.GONE);
				//imageView.bringToFront();
				mTabs[1].getLocationOnScreen(locationBt);
				Log.e("坐标", location[0]+"");
				Animation translateAnimation = new TranslateAnimation(location[0],locationBt[0],0,0);  
				translateAnimation.setDuration(TIME); 
				translateAnimation.setFillAfter(true);
				translateAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						Log.e("开始坐标1：",location[0]+" "+locationBt[0]);
					}
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
//						mImages[0].clearAnimation();  
//						Log.e("长度：", mTabs[1].getWidth()+":"+mImages[0].getWidth());
//						mImages[0].layout(mTabs[1].getLeft(), 41,0,51);
//						Log.e("坐标1：",mImages[0].getLeft()+" "+mImages[0].getTop()+" "+mImages[0].getRight()+" "+mImages[0].getBottom());
					
						location[0]=locationBt[0];
					}
				});
				relativeLayout.startAnimation(translateAnimation);
				util(index);
			}
		});
		mTabs[2].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index=2;
				mTabs[0].setTextColor(Color.WHITE);
				mTabs[1].setTextColor(Color.WHITE);
				mTabs[2].setTextColor(Color.GREEN);
				mTabs[3].setTextColor(Color.WHITE);
				//	mImages[0].setVisibility(View.GONE);
				//	mImages[1].setVisibility(View.GONE);
				//	mImages[2].setVisibility(View.VISIBLE);
				//	mImages[3].setVisibility(View.GONE);
				//imageView.bringToFront();
				mTabs[2].getLocationOnScreen(locationBt);
				Log.e("坐标", location[0]+"");
				Animation translateAnimation = new TranslateAnimation(location[0],locationBt[0],0,0);  
				translateAnimation.setDuration(TIME); 
				translateAnimation.setFillAfter(true);
				translateAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						Log.e("开始坐标1：",location[0]+" "+locationBt[0]);
					}
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
//						mImages[0].clearAnimation();  
//						Log.e("长度：", mTabs[1].getWidth()+":"+mImages[0].getWidth());
//						mImages[0].layout(mTabs[1].getLeft(), 41,0,51);
//						Log.e("坐标1：",mImages[0].getLeft()+" "+mImages[0].getTop()+" "+mImages[0].getRight()+" "+mImages[0].getBottom());
					
						location[0]=locationBt[0];
					}
				});
				relativeLayout.startAnimation(translateAnimation);
				util(index);
			}
		});
		mTabs[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				index=3;
				mTabs[0].setTextColor(Color.WHITE);
				mTabs[1].setTextColor(Color.WHITE);
				mTabs[2].setTextColor(Color.WHITE);
				mTabs[3].setTextColor(Color.GREEN);

				//mImages[0].setVisibility(View.GONE);
				//mImages[1].setVisibility(View.GONE);
				//	mImages[2].setVisibility(View.GONE);
				//	mImages[3].setVisibility(View.VISIBLE);
				//imageView.bringToFront();
				mTabs[3].getLocationOnScreen(locationBt);
				Log.e("坐标", location[0]+"");
				Animation translateAnimation = new TranslateAnimation(location[0],locationBt[0],0,0);  
				translateAnimation.setDuration(TIME); 
				translateAnimation.setFillAfter(true);
				translateAnimation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub
						Log.e("开始坐标1：",location[0]+" "+locationBt[0]);
					}
					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onAnimationEnd(Animation arg0) {
						// TODO Auto-generated method stub
//						mImages[0].clearAnimation();  
//						Log.e("长度：", mTabs[1].getWidth()+":"+mImages[0].getWidth());
//						mImages[0].layout(mTabs[1].getLeft(), 41,0,51);
//						Log.e("坐标1：",mImages[0].getLeft()+" "+mImages[0].getTop()+" "+mImages[0].getRight()+" "+mImages[0].getBottom());
					
						location[0]=locationBt[0];
					}
				});
				relativeLayout.startAnimation(translateAnimation);
				util(index);
			}
		});
		/*	if (currentTabIndex != index) {
			transaction.hide(fragments[currentTabIndex]);
			if (!fragments[index].isAdded()) {
				transaction.add(R.id.fragment_container, fragments[index]);
			}
			transaction.show(fragments[index]).commit();
		}
		mTabs[currentTabIndex].setSelected(false);
		mImages[currentTabIndex].setVisibility(View.INVISIBLE);
		// 把当前tab设为选中状态
		mTabs[index].setSelected(true);
		mImages[index].setVisibility(View.VISIBLE);
		currentTabIndex = index;*/
	}
	public void util(int index){
		if (currentTabIndex != index) {
			FragmentTransaction ft=getChildFragmentManager().beginTransaction();
			ft.hide(fragments[currentTabIndex]);
			if (!fragments[index].isAdded()) {
				ft.add(R.id.fragment_container, fragments[index]);
			}
			ft.show(fragments[index]).commit();
		}
		mTabs[currentTabIndex].setSelected(false);
		//	mImages[currentTabIndex].setVisibility(View.INVISIBLE);
		// 把当前tab设为选中状态
		mTabs[index].setSelected(true);
		//	mImages[index].setVisibility(View.VISIBLE);
		currentTabIndex = index;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// 长按前两个不弹menu
		if (((AdapterContextMenuInfo) menuInfo).position > 2) {
			getActivity().getMenuInflater().inflate(R.menu.context_contact_list, menu);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.delete_contact) {
			HXUser tobeDeleteUser = adapter.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			// 删除此联系人
			deleteContact(tobeDeleteUser);
			// 删除相关的邀请消息
			InviteMessgeDao dao = new InviteMessgeDao(getActivity());
			dao.deleteMessage(tobeDeleteUser.getUsername());
			return true;
		}else if(item.getItemId() == R.id.add_to_blacklist){
			HXUser user = adapter.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position);
			moveToBlacklist(user.getUsername());
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		this.hidden = hidden;
		if (!hidden) {
			//refresh();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!hidden) {
			//refresh();
		}
	}

	/**
	 * 删除联系人
	 * 
	 * @param toDeleteUser
	 */
	public void deleteContact(final HXUser tobeDeleteUser) {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在删除...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		//		new Thread(new Runnable() {
		//			public void run() {
		//				try {
		//					//EMContactManager.getInstance().deleteContact(tobeDeleteUser.getUsername());
		//					// 删除db和内存中此用户的数据
		//					UserDao dao = new UserDao(getActivity());
		//					dao.deleteContact(tobeDeleteUser.getUsername());
		//					AlUApplication.getInstance().getContactList().remove(tobeDeleteUser.getUsername());
		//					getActivity().runOnUiThread(new Runnable() {
		//						public void run() {
		//							pd.dismiss();
		//							adapter.remove(tobeDeleteUser);
		//							adapter.notifyDataSetChanged();
		//
		//						}
		//					});
		//				} catch (final Exception e) {
		//					getActivity().runOnUiThread(new Runnable() {
		//						public void run() {
		//							pd.dismiss();
		//							Toast.makeText(getActivity(), "删除失败: " + e.getMessage(), 1).show();
		//						}
		//					});
		//
		//				}
		//
		//			}
		//		}).start();

	}

	/**
	 * 把user移入到黑名单
	 */
	private void moveToBlacklist(final String username){
		final ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setMessage("正在移入黑名单...");
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		new Thread(new Runnable() {
			public void run() {
				//try {
				//加入到黑名单
				//EMContactManager.getInstance().addUserToBlackList(username,false);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						Toast.makeText(getActivity(), "移入黑名单成功", 0).show();
						//refresh();
					}
				});
				//} 
				//				catch (EaseMobException e) {
				//					e.printStackTrace();
				//					getActivity().runOnUiThread(new Runnable() {
				//						public void run() {
				//							pd.dismiss();
				//							Toast.makeText(getActivity(), "移入黑名单失败", 0).show();
				//						}
				//					});
				//				}
			}
		}).start();

	}

	// 刷新ui
	//	public void refresh() {
	//		try {
	//			// 可能会在子线程中调到这方法
	//			getActivity().runOnUiThread(new Runnable() {
	//				public void run() {
	//					getContactList();
	//					adapter.notifyDataSetChanged();
	//
	//				}
	//			});
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

	/**
	 * 获取联系人列表，并过滤掉黑名单和排序
	 */
	private void getContactList() {
		contactList.clear();
		//获取本地好友列表
		Map<String, HXUser> users = AlUApplication.getInstance().getContactList();
		Iterator<Entry<String, HXUser>> iterator = users.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, HXUser> entry = iterator.next();
			if (!entry.getKey().equals(Constant.NEW_FRIENDS_USERNAME) && !entry.getKey().equals(Constant.GROUP_USERNAME)
					&& !blackList.contains(entry.getKey()))
				contactList.add(entry.getValue());
		}
		// 排序
		Collections.sort(contactList, new Comparator<HXUser>() {

			@Override
			public int compare(HXUser lhs, HXUser rhs) {
				return lhs.getUsername().compareTo(rhs.getUsername());
			}
		});

		// 加入"申请与通知"和"群聊"
		contactList.add(0, users.get(Constant.GROUP_USERNAME));
		// 把"申请与通知"添加到首位
		contactList.add(0, users.get(Constant.NEW_FRIENDS_USERNAME));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if(((MainActivity)getActivity()).isConflict)
			outState.putBoolean("isConflict", true);
		super.onSaveInstanceState(outState);

	}
}
