package devas.com.whatchaap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class Friends extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Thread f;
    boolean stopped = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
     ListView lv;

    //   private OnFragmentInteractionListener mListener;

    final Handler mHandler = new Handler();
    public Friends() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Friends.
     */
    // TODO: Rename and change types and number of parameters
    public static Friends newInstance(String param1, String param2) {
        Friends fragment = new Friends();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        lv = (ListView)v.findViewById(R.id.friendListView);
       // final MainActivity activity = ((MainActivity) getActivity());
     //   final ArrayList<String>[] names = new ArrayList[]{new ArrayList<>()};

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                ArrayList<String> names = activity.getmService().xmpp.getFriends();
//                String[] dost = new String[1500];
//                Log.d("dost", names.get(0));
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                        android.R.layout.simple_list_item_1, android.R.id.text1, names);
//                lv.setAdapter(adapter);
//
//            }
//        }, 15000);
        dispFriends();

        friendThread ft = new friendThread();
        f = new Thread(ft);
        f.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view.findViewById(R.id.friendName);
                Intent i = new Intent(getActivity(),Chats.class);
                i.putExtra("user2", tv.getText().toString());
                startActivity(i);

            }
        });

       // Log.d("Chutiya", names[0].get(0));



        return v;
    }
    public void dispFriends() {
        UserDetails u = new UserDetails(getActivity());

        String[] n = u.getUserfriends().split("=");

        HashMap<String, String> hm = new HashMap<>();
        ArrayList<HashMap<String, String>> al = new ArrayList<>();

        for(String t : n) {
            System.out.println("naye " + t);
            hm.put("friend", t.trim());
            al.add(hm);
        }

        SimpleAdapter sa =  new SimpleAdapter(
                getActivity() ,
                al,
                R.layout.friend_item,
                new String[] { "friend" },
                new int[] { R.id.friendName});

       // adapter1.notifyDataSetChanged();
        //adapter1.add();
        lv.setAdapter(sa);


    }
    public final class friendThread implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(10000);
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            // Write your code here to update the UI.
                            dispFriends();
                        }
                    });
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    break;

                }
            }

        }
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("Thread", "interrupted");
        f.interrupt();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Thread", "interrupted");
        f.interrupt();
    }
}
