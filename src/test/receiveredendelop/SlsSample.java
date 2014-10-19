package test.receiveredendelop;

import java.util.Date;
import java.util.Vector;

import com.aliyun.openservices.sls.LogContent;
import com.aliyun.openservices.sls.LogItem;
import com.aliyun.openservices.sls.LogMeta;
import com.aliyun.openservices.sls.LogMetaItem;
import com.aliyun.openservices.sls.LogResult;
import com.aliyun.openservices.sls.SLSClient;
import com.aliyun.openservices.sls.SlsException;

public class SlsSample {

	public static void main(String args[]) throws SlsException,
			InterruptedException {

		String accessId = "aOTDNTIGRCUYUoCi";
		String accessKey = "c0BMG80Z9frfheWV3PKNeN4Nbkw4NS";

		String project = "sls-test";
		String host = "http://cn-hangzhou.sls.aliyuncs.com";
		// or create a client with a setting endpoint address
		SLSClient client = new SLSClient(accessId, accessKey, host);
		// generate two logs

		int size = 0;
		for (int i = 0; i < 0; i++) {
			Vector<LogItem> logGroup = new Vector<LogItem>();
			LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
			logItem.PushBack("level", "info");
			logItem.PushBack("name", String.valueOf(i));
			logItem.PushBack("message", "it's a test message");

			logGroup.add(logItem);

			LogItem logItem2 = new LogItem((int) (new Date().getTime() / 1000));
			logItem2.PushBack("level", "error");
			logItem2.PushBack("name", String.valueOf(i));
			logItem2.PushBack("message", "it's a test message");
			logGroup.add(logItem2);

			try {
				client.PutData(project, "sls_test", "sls_topic_1", logGroup);
				size++;
			} catch (SlsException e) {
				System.out.println("error code :" + e.GetErrorCode());
				System.out.println("error message :" + e.GetErrorMessage());
				return;
			}
			
		}
		System.out.println("success lines" + String.valueOf(size));

		// get the category from sls server
		try {
			Vector<String> categorys = client.ListCategory(project);
			System.out.println(categorys.toString());
		} catch (SlsException e) {
			System.out.println("error code :" + e.GetErrorCode());
			System.out.println("error message :" + e.GetErrorMessage());
			System.out.println("error message :" + e.toString());
		}
		
		
		// get the topic from sls server
		try {
			Vector<String> topics = client.ListTopic(project, "sls_test", "");
			System.out.println(topics.toString());
		} catch (SlsException e) {
			System.out.println("error code :" + e.GetErrorCode());
			System.out.println("error message :" + e.GetErrorMessage());
		}
		
		try {
			LogMeta logMeta = client.GetDataMeta(project, "sls_test",
					"sls_topic", (int) (new Date().getTime() / 1000 - 10000),
					(int) (new Date().getTime() / 1000 + 10));
			System.out.println("result : " + logMeta.mTotalogNum);
			System.out.println("more_data : " + logMeta.mMoreData);
			for (LogMetaItem metaItem : logMeta.mlogMetaItem) {
				System.out.println("beginTime:" + metaItem.mBeginTime
						+ " endTime:" + metaItem.mEndTime + " logNum:"
						+ metaItem.mLogNum + " moreData:" + metaItem.mMoreData);
			}
		} catch (SlsException e) {
			System.out.println("error code :" + e.GetErrorCode());
			System.out.println("error message :" + e.GetErrorMessage());
		}

		// get the data from sls server, it should return the logs contain
		// "error" key words
		try {
			LogResult result1 = client.GetData(project, "sls_test",
					"sls_topic", (int) (new Date().getTime() / 1000 - 10000),
					(int) (new Date().getTime() / 1000 + 10));
			for (LogItem item : result1.logDatas) {
				System.out.println("time : " + item.logTime);
				for (LogContent content : item.contents) {
					System.out.println(content.key + ":" + content.value);
				}
			}
		} catch (SlsException e) {
			System.out.println("error code :" + e.GetErrorCode());
			System.out.println("error message :" + e.GetErrorMessage());
		}

	}
}
