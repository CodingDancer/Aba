package com.aba.datasource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.xml.crypto.Data;

import model.Place;
import model.Tourist;

public class Simulater {
	
	String datatime = "0";
	int flag=0;
	
	//��controller���ȡԭʼ���ݲ�����
	public void getParm(int[] num,String[] prop,String time){
		datatime=time;
		int[][] propList= new int[5][5]; 
		for(int i=0;i<5;i++)
		{
			propList[i][0]=num[i];
			String propString = prop[i];
			String[] s = propString.split("/");
			for(int j= 1;j<5;j++)
			{
				propList[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		CreateClum(propList);
	}
	
	//�������ݽڵ�
	public void CreateNode(int palaceID,String phoneNum,String source,String time)
	{
		java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			
			date = formatter.parse(time);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Tourist tourist = new Tourist();
		Place place = new Place();
		place.setPlaceID(palaceID);
		tourist.setPlaceName(place.getName());
		tourist.setPhoneNum(phoneNum);
		tourist.setSource(source);
		tourist.setTime(date);
		flag++;
		System.out.println("��"+flag+"����"
		                   +palaceID+"��������"
				           +source+"������,�绰����Ϊ"
		                   +phoneNum+"�����ݽڵ��Ѿ�����,ʱ��Ϊ"+time);
		System.out.println("");
	}
	//���ݽ������������ݷ�����������
	public void CreateClum(int[][] dataSet)
	{

		Random random =new Random();
		//ÿ������ѭ��һ����������
		for(int i=0;i<5;i++)
		{
			int sum = dataSet[i][0];
			int proSum = dataSet[i][1]+dataSet[i][2]+dataSet[i][3]+dataSet[i][4];
			//ÿ�������ص�����
			int[] num = {sum/proSum*dataSet[i][1],
					     sum/proSum*dataSet[i][2],
					     sum/proSum*dataSet[i][3],
					     sum-sum/proSum*dataSet[i][1]-
					     sum/proSum*dataSet[i][2]-
					     sum/proSum*dataSet[i][3]};
			//ÿ��������һ��ѭ��������ָ����Ŀ������
			for(int a=0;a<num[0];a++)
			{
				//������ɵ绰���룬ǰ׺һ�±�ʾ��������������
				String ponenum = "001";
				ponenum = ponenum+random.nextInt(90000000);
				CreateNode(i, ponenum, "001", datatime);
			}
			for(int a=0;a<num[1];a++)
			{
				//������ɵ绰���룬ǰ׺һ��,��ʾ��������������
				String ponenum = "002";
				ponenum = ponenum+random.nextInt(90000000);
				CreateNode(i, ponenum, "002", datatime);
			}
			for(int a=0;a<num[2];a++)
			{
				//������ɵ绰���룬ǰ׺һ��,��ʾ��������������
				String ponenum = "003";
				ponenum = ponenum+random.nextInt(90000000);
				CreateNode(i, ponenum, "003", datatime);
			}
			for(int a=0;a<num[3];a++)
			{
				//������ɵ绰���룬ǰ׺һ�±�ʾ��������������
				String ponenum = "004";
				ponenum = ponenum+random.nextInt(90000000);
				CreateNode(i, ponenum, "004", datatime);
			}
		}
	}
	
	

}
