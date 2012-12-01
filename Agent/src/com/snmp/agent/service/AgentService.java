package com.snmp.agent.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import com.snmp.agent.model.MIBtree;
import org.snmp4j.*;
import org.snmp4j.asn1.BERInputStream;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

public class AgentService extends Service implements CommandResponder {

    /** Keeps track of all current registered clients. */
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();
    /** Holds last value set by a client. */
    int mValue = 0;

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_SET_VALUE = 3;
    public static final int MSG_SNMP_REQUEST_RECEIVED = 4;
    public static final int MSN_SEND_DANGER_TRAP = 5;

    public static String lastRequestReceived = "";

    private Snmp snmp;
    private static ArrayList<Address> registeredManagers = null;

    private MIBtree MIB_MAP;
    private Timer timer;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_VALUE:
                    mValue = msg.arg1;
                    sendMessageToClients(MSG_SET_VALUE);
                    break;
                case MSN_SEND_DANGER_TRAP:
                    new SendTrap().execute();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private void sendMessageToClients(int msgCode) {
        for (int i=mClients.size()-1; i>=0; i--) {
            try {
                mClients.get(i).send(Message.obtain(null,
                        msgCode, 0, 0));
            } catch (RemoteException e) {
                // The client is dead.  Remove it from the list;
                // we are going through the list from back to front
                // so this is safe to do inside the loop.
                mClients.remove(i);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        /*MIB_MAP = new MIBtree();
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,2,2,1,1,4}), new Integer32(2)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,2,2,1,1,3}), new OctetString("BLALBAL3")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,1}), new OctetString("BLALBAL1")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2}), new OctetString("BLALBAL2")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,2,2,1,1,4}), new Integer32(1)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,2,2,1,1,4}), new Integer32(3)));
        MIB_MAP.print();
        System.out.println(MIB_MAP.get(new OID(new int[] {1,3,6,1,2,1,1,1,0})).toString());
        MIB_MAP.set(new VariableBinding(new OID(new int[]{1, 3, 6, 1, 2, 1, 1, 1, 0}), new OctetString("BLALBAL23")));
        System.out.println(MIB_MAP.get(new OID(new int[] {1,3,6,1,2,1,1,1,0})).toString());
        System.out.println(MIB_MAP.getNext(new OID(new int[]{1, 3, 6, 2, 2, 1, 1, 3})).toString());
        System.out.println(MIB_MAP.getNext(new OID(new int[] {1,3,6,1,2,1,1,2,0})).toString()); */
        initMIBWithDefault();
        timer = new Timer();
        timer.scheduleAtFixedRate(new RefreshMIBData(), 0, 50000);
        new AgentListener().start();
    }

    private void initMIBWithDefault(){
        MIB_MAP = MIBtree.getInstance();
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,1,1,0}), new OctetString("Modelo x")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,1,1,0}), new OctetString("Modelo bla")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,1,2,0}), new OctetString("4.0")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,1,3,0}), new TimeTicks(100000)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,1,0}), new Integer32(2)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,1,1}), new Integer32(1)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,1,2}), new Integer32(2)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,2,1}), new OctetString("BALBA")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,2,2}), new OctetString("BALBA2")));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,3,1}), new TimeTicks(98361456)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,3,2}), new TimeTicks(234452652)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,4,1}), new Integer32(4568)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,2,2,1,4,2}), new Integer32(6456)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,3,1,0}), new Integer32(1)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,3,2,0}), new Integer32(100)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,3,3,0}), new Integer32(0)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,3,4,0}), new Integer32(0)));
        MIB_MAP.addNode(new VariableBinding(new OID(new int[] {1,3,6,1,4,1,12619,1,3,5,0}), new Integer32(1)));
        MIB_MAP.print();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy() {

    }

    private class SendTrap extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            PDUv1 pdu = new PDUv1();
            pdu.setType(PDU.V1TRAP);
            pdu.setGenericTrap(PDUv1.COLDSTART);
            pdu.add(new VariableBinding(new OID(new int[]{1, 3, 6, 1, 2, 1, 1, 2})));

            // Specify receiver
            Address targetAddress = new UdpAddress("192.168.0.102/1610");
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version1);
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);


            try {
                snmp.trap(pdu, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class AgentListener extends Thread {
        public void run() {
            try {

                initSnmp();

                snmp.listen();

            }  catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void initSnmp(){
            try {
                TransportMapping transport = null;
                transport = new DefaultUdpTransportMapping(new UdpAddress("0.0.0.0/1610"));

                snmp = new Snmp(transport);


                snmp.addCommandResponder(AgentService.this);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Address> getRegisteredManagers(){
        if(registeredManagers == null) return new ArrayList<Address>();
        return registeredManagers;
    }

    private static void registerManager(Address address){
        if(registeredManagers == null){
            registeredManagers = new ArrayList<Address>();
            registeredManagers.add(address);
        } else {
            boolean exists = false;
            for(Address a : registeredManagers){
                if(a.toString().equals(address.toString())) exists=true;
            }
            if(!exists) registeredManagers.add(address);
        }
    }

    @Override
    public synchronized void processPdu(CommandResponderEvent commandResponderEvent) {
        PDU command = (PDU) commandResponderEvent.getPDU().clone();
        registerManager(commandResponderEvent.getPeerAddress());

        if (command != null) {
            lastRequestReceived = command.toString() + " " + commandResponderEvent.getPeerAddress();
            sendMessageToClients(MSG_SNMP_REQUEST_RECEIVED);
            if (command.getType() == PDU.GET){
                handleGetRequest(command);
            } else if(command.getType() == PDU.GETNEXT){
                handleGetNextRequest(command);
            }
            Address address = commandResponderEvent.getPeerAddress();
            sendResponse(address, command);
        }
    }

    private void sendResponse(Address address, PDU command) {
        System.out.println(command.toString());
        // Specify receiver
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setVersion(SnmpConstants.version1);
        target.setAddress(address);
        target.setRetries(2);
        target.setTimeout(1500);

        try {
            snmp.send(command, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleGetNextRequest(PDU command) {
        VariableBinding varBind;
        for(int i = 0; i < command.size(); i++){
            varBind = command.get(i);
            command.set(i, answerForGetNext(varBind.getOid()));
        }
    }

    private VariableBinding answerForGetNext(OID oid) {
        VariableBinding vb = MIB_MAP.getNext(oid);
        return vb;
    }

    private void handleGetRequest(PDU command) {
        VariableBinding varBind;
        for(int i = 0; i < command.size(); i++){
            varBind = command.get(i);
            varBind.setVariable(answerForGet(varBind.getOid()));

        }
    }

    private Variable answerForGet(OID oid) {
        VariableBinding vb = MIB_MAP.get(oid);
        return vb.getVariable();
    }


    class RefreshMIBData extends TimerTask {
        SystemInformation systemInformation;

        public RefreshMIBData(){
            systemInformation = new SystemInformation(AgentService.this);
        }

        public void run() {
            systemInformation.updateSystemInformation();
        }
    }
}
