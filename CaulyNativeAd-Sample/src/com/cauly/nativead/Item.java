package com.cauly.nativead;


public class Item
{
	 public int img, icon;
	 public String  title, subTitle, description, tag;
	 Item(int img, String title, String subTitle,String description,String tag)
	 {
		 this.img = img;
		 this.title =title;
		 this.subTitle = subTitle;
		 this.tag = tag;
		 this.description = description;
	 }
}