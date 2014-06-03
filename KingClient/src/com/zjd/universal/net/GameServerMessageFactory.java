
package com.zjd.universal.net;

import receive.CanStartGamePKMessageReceive2005;
import receive.CreatePKResultMessageReceive2002;
import receive.EndGamePKResultMessageReceive2007;
import receive.ForceLeavePKResultMessageReceive2012;
import receive.HeartMessageRecevie2000;
import receive.HostLeavePKResultMessageRecevie2009;
import receive.JoinPKResultMessageReceive2003;
import receive.CrashLeavePKResultMessageRecevie2004;
import receive.NoCanStartGamePKMessageReceive2010;
import receive.NormalLeavePKResultMessageRecevie2011;
import receive.RepeatLoginErrorMessageRecevie2014;
import receive.RoleNameErrorMessageRecevie2015;
import receive.RoomPKBeginMessageReceive2013;
import receive.RoomPKMessageReceive2001;
import receive.RoomPKFinishMessageReceive2008;
import receive.SocketMessageReceived;
import receive.StartGamePKResultMessageReceive2006;


public class GameServerMessageFactory {


    public static SocketMessageReceived getMessage(int mainID) {
    	switch (mainID) {
    	case 2000:
			return new HeartMessageRecevie2000();
		case 2001:
			return new RoomPKMessageReceive2001();
		case 2002:
			return new CreatePKResultMessageReceive2002();
		case 2003:
			return new JoinPKResultMessageReceive2003();
		case 2004:
			return new CrashLeavePKResultMessageRecevie2004();
		case 2005:
			return new CanStartGamePKMessageReceive2005();
		case 2006:
			return new StartGamePKResultMessageReceive2006();
		case 2007:
			return new EndGamePKResultMessageReceive2007();
		case 2008:
			return new RoomPKFinishMessageReceive2008();
		case 2009:
			return new HostLeavePKResultMessageRecevie2009();
		case 2010:
			return new NoCanStartGamePKMessageReceive2010();
		case 2011:
			return new NormalLeavePKResultMessageRecevie2011();
		case 2012:
			return new ForceLeavePKResultMessageReceive2012();
		case 2013:
			return new RoomPKBeginMessageReceive2013();
		case 2014:
			return new RepeatLoginErrorMessageRecevie2014();
		case 2015:
			return new RoleNameErrorMessageRecevie2015();
		default:
			break;
		}
//        switch (mainID) {
//            case NetCommand.MDM_GR_INFO:
//                if (subID == NetCommand.SUB_GR_SERVER_INFO)
//                    return new Receive3_100RoomInforMessage();
//                break;
//            case NetCommand.MDM_HEART:
//                if (subID == NetCommand.SUB_HEART)
//                    return new Receive0_1HeartMessage();
//                break;
//            case NetCommand.MDM_GP_LOGON:
//                switch (subID) {
//                    case NetCommand.SUB_GR_LOGON_ERROR:
//                        return new Receive1_101GRLogonErrorMessage();
//                    case NetCommand.SUB_GR_LOGON_FINISH:
//                        return new Receive1_102GRLoginFinishMessage();
//                }
//                break;
//            case NetCommand.MDM_GR_USER:
//                switch (subID) {
//                    case NetCommand.SUB_GR_USER_COME:
//                        return new Receive2_100GRUserComeMessage();
//                    case NetCommand.SUB_GR_USER_STATUS:
//                        return new Receive2_101GRUserStatus();
//                    case NetCommand.SUB_GR_USER_SCORE:
//                        return new Receive2_102GRUserScore();
//                    case NetCommand.SUB_GR_SIT_FAILED:
//                        return new Receive2_103_GRSitFailed();
//                }
//                break;
//            case NetCommand.MDM_GR_STATUS:
//                switch (subID) {
//                    case NetCommand.SUB_GR_TABLE_INFO:
//                        return new Receive4_100GRTableInfoMessage();
//                }
//                break;
//            case NetCommand.MDM_GR_SYSTEM:
//                switch (subID) {
//                    case NetCommand.SUB_GR_MESSAGE:// 升降档子消息
//                        return new Receive10_100GameInforMessage();
//                }
//            case NetCommand.MDM_GF_FRAME:
//                switch (subID) {
//                    case NetCommand.SUB_GF_OPTION:
//                        return new Receive101_100CMDGFOptionMessage();
//                    case NetCommand.SUB_GF_SCENE:
//                        return new Receive101_101CMDSStatusFreeMessage();
//                    case NetCommand.SUB_GF_MESSAGE:// 消息 和 升降档
//                        return new Receive101_300GameInforMessage();
////                    case NetCommand.SUB_GF_PlaySound:
////                        return new Receive101_555CMDGFPlaySoundMessage();
//                }
//                break;
//            case NetCommand.MDM_GR_SERVER_INFO:
//            	 switch (subID) {
//            	   case NetCommand.SUB_CS_SERVER_ONLINE_COUNT:
//                       return new Receive11_3ServerOnLineCountMessage();
//            	 }
//            	break;
//            case NetCommand.MDM_GF_GAME:
//                switch (subID) {
//                    case NetCommand.SUB_GR_CLIENT_START_GAME:
//                        return new Receive100_407NullSubGRClientStartGameMessage();
//                    case NetCommand.SUB_TASK_FINISH:
//                        return new Receive100_411TaskFinishMessage();
//                    case NetCommand.SUB_GF_CELL:
//                        return new Receive100_600CMDCellMessage();
//                    case 601:
//                        return new Receive100_601CMDEXPRESSIONMessage();
//                }
//                break;
//
//        }
        return null;
    }
}
