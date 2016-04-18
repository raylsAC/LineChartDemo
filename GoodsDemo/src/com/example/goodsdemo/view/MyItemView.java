package com.example.goodsdemo.view;

import com.example.goodsdemo.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyItemView extends View{

	public Context mContext;
	private int xlength;
	private int count = 0;
	private int tmpcount = 0;
	private float per = 0;
	private float sum = per;
	
	private int bottompointX = 60;	//底部像素点的x坐标--+10--+10--+5
	public int bottompointY = 600;	
	
	private int VlineHeight;	
	public static int VlinedefaultHeight = 500;	
	private int VlineStartX = bottompointX;
	private int VlineStartY = bottompointY;
	private int VlineStopX = bottompointX;
	private int VlineStopY;
	
	private int linklineStartX;
	private int linklineStartY;
	private int linklineStopX;
	private int linklineStopY;
	
	private int HlineStartX = 0;
	private int HlineStartY = bottompointY;
	private int HlineStopX = 600;
	private int HlineStopY = bottompointY;
	
	public int Default_topcircleR = 11;
	private int topcircleX = bottompointX;
	private int topcircleY;
	
	public int bottomcircleR = 25;
	private int bottomcircleX = bottompointX;
	private int bottomcircleY = bottompointY + bottomcircleR + 20;
	
	private int bottomrectfLeft = bottomcircleX - 40;
	private int bottomrectfTop = bottomcircleY - 20;
	private int bottomrectfRight = bottomcircleX + 40;
	private int bottomrectfBottom = bottomcircleY + 20;
	
	public String toptvText = "600";
	public int toptvsize = 24;
	private int toptvX;
	private int toptvY;
	
	public String bottomtvText = "2015";
	public int bottomtvsize = 26;
	private int bottomtvX;
	private int bottomtvY;
	
	
	private boolean isSelect = false;
	public boolean isLink = false;
	public boolean istvUp = true;
	private boolean isYear = false;
	public boolean isZero = false;
	
	private Paint countPaint = new Paint();
	private Paint circlePaint = new Paint();
	private Paint circlePaintF = new Paint();
	private Paint BottomcirclePaint = new Paint();
	private Paint linepPaint = new Paint();
	private Paint linklinepPaint = new Paint();
	private Paint pointPaint = new Paint();
	private Paint bottomTvPaint = new Paint();
	
	public MyItemView(Context mContext) {
		this(mContext, null);
	}

	public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		initPaint();
	}

	public MyItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	private void initPaint() {

		//initcountpaint
		countPaint.setAntiAlias(true);
		countPaint.setColor(mContext.getResources().getColor(R.color.normal_red_color));
		countPaint.setTextSize(toptvsize);
		
		//initcieclepaint
		circlePaintF.setAntiAlias(true);
		circlePaintF.setColor(mContext.getResources().getColor(R.color.normal_white_color));
		circlePaintF.setStrokeWidth(3);
		circlePaintF.setStyle(Style.FILL);
		
		circlePaint.setAntiAlias(true);
		circlePaint.setColor(mContext.getResources().getColor(R.color.normal_red_color));
		circlePaint.setStrokeWidth(3);
		circlePaint.setStyle(Style.STROKE);
		
		//initBottomcirclePaint
		BottomcirclePaint.setAntiAlias(true);
		BottomcirclePaint.setColor(mContext.getResources().getColor(R.color.normal_red_color));
		BottomcirclePaint.setStrokeWidth(3);
		BottomcirclePaint.setStyle(Style.FILL);
		
		//initlinklinepaint
		linklinepPaint.setAntiAlias(true);
		linklinepPaint.setColor(mContext.getResources().getColor(R.color.normal_red_color));
		linklinepPaint.setStrokeWidth(2);
		
		//initlinepaint
		linepPaint.setAntiAlias(true);
		linepPaint.setColor(mContext.getResources().getColor(R.color.normal_gray_color));
		linepPaint.setStrokeWidth(2);
		
		//initpointpaint
		pointPaint.setAntiAlias(true);
		pointPaint.setColor(mContext.getResources().getColor(R.color.normal_gray_color));
		pointPaint.setStrokeWidth(1);
		pointPaint.setStyle(Style.FILL);
		
		//initbottomtvpaint
		bottomTvPaint.setAntiAlias(true);
		bottomTvPaint.setTextSize(bottomtvsize);
	}
	
	public void setToptvUp() {
		istvUp = true;
//		invalidate();
	}
	
	public void setToptvDown() {
		istvUp = false;
//		invalidate();
	}
	
	public void setBottomCircleVisible() {
		isSelect = true;
		invalidate();
	}
	
	public void setBottomCircleinVisible() {
		isSelect = false;
		invalidate();
	}
	
	public void isYear() {
		isYear = true;
		invalidate();
	}
	
	//配置item
	public void setChartItem(int vlineHeight, String toptext, String bottomtext) {
		VlineHeight = vlineHeight;
		topcircleY = bottompointY - VlineHeight;
		VlineStopY = topcircleY + Default_topcircleR;
		if (VlineHeight < VlinedefaultHeight/8) {
			istvUp = true;
		}
		settoptvText(toptext);
		setbottomtvText(bottomtext);
		invalidate();
	}
	
	//顶部设置文本
	private void settoptvText(String toptext) {
		toptvText = toptext;
		toptvX = bottompointX-((toptvText.length() * (toptvsize/2))/2 + (toptvsize - Default_topcircleR - 10)/2);
		if (istvUp) {
			toptvY = VlineStopY - 2 * Default_topcircleR - 10;
		}else {
			toptvY = VlineStopY + toptvsize + 10;
		}
	}

	//底部设置文本
	private void setbottomtvText(String bottomtext) {
		bottomtvText = bottomtext;
		bottomtvX = bottompointX-((bottomtvText.length() * (bottomtvsize/2))/2 + (bottomtvsize - Default_topcircleR - 10)/2);
		bottomtvY = bottomcircleY + 9;
	}
	
	public void drawlinkline(int startY, int stopX, int stopY) {
		isLink = true;
		linklineStartX = bottompointX;
		linklineStartY = bottompointY - startY ;
		linklineStopX = stopX;
		linklineStopY = bottompointY - stopY;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//绘制底部像素点
		canvas.drawCircle(bottompointX, bottompointY, 4, pointPaint);
		//绘制竖直直线
		canvas.drawLine(VlineStartX, VlineStartY, VlineStopX, VlineStopY, linepPaint);
		//绘制底部线
		if (isZero) {
			canvas.drawLine(bottompointX, HlineStartY, HlineStopX, HlineStopY, linepPaint);
		}else {
			canvas.drawLine(HlineStartX, HlineStartY, HlineStopX, HlineStopY, linepPaint);
		}
		//绘制两个点之间的连线
		if (isLink) {
			/*if (isZero) {
				canvas.drawLine(linklineStartX, bottompointY, 0, bottompointY, linklinepPaint);
				System.out.println("-----------2");
				isZero = false;
			}
			canvas.drawLine(linklineStartX, linklineStartY, linklineStopX, linklineStopY, linklinepPaint);
			isLink = false;*/
			
			//以下代码为添加动画
			//**********************************************
			if ((linklineStopY - linklineStartY) != 0) {
				per = (float) (Math.abs(linklineStopY - linklineStartY)*0.01);
			}else {
				per = 0;
				sum = 0;
			}
			/*if (isZero) {
				xlength = linklineStopX;
			}else {
				xlength = linklineStopX - linklineStartX;
			}*/
			xlength = linklineStopX;
			if (count < xlength) {
				count++;
				per = sum + per;
				sum = per;
				if (linklineStopY > linklineStartY) {//后一个比前一个低
					canvas.drawLine(linklineStartX, linklineStartY, linklineStartX+count, linklineStartY+per, linklinepPaint);
				}else {
					/*if (isZero) {
						if (count <= linklineStartX) {
							canvas.drawLine(0, bottompointY, count, bottompointY, linklinepPaint);
						}else {
							canvas.drawLine(linklineStartX, bottompointY, 0, bottompointY, linklinepPaint);
							canvas.drawLine(linklineStartX, linklineStartY, linklineStartX+count, linklineStartY-per, linklinepPaint);
						}
					}else {
						canvas.drawLine(linklineStartX, linklineStartY, linklineStartX+count, linklineStartY-per, linklinepPaint);
					}*/
					/*if (count < (xlength-2*linklineStartX)) {
						canvas.drawLine(linklineStartX, linklineStartY, linklineStartX+count, linklineStartY-per, linklinepPaint);
					}else {
						if (isZero) {
							if (count >= (xlength-2*linklineStartX)) {
								if (tmpcount <= linklineStartX) {
									canvas.drawLine(0, bottompointY, tmpcount++, bottompointY, linklinepPaint);
								}
							}
						}*/
						canvas.drawLine(linklineStartX, linklineStartY, linklineStartX+count, linklineStartY-per, linklinepPaint);
//					}
					
				}
				invalidate();
			}else {
				if (isZero) {
					canvas.drawLine(linklineStartX, bottompointY, 0, bottompointY, linklinepPaint);
					isZero = false;
				}
				canvas.drawLine(linklineStartX, linklineStartY, linklineStopX, linklineStopY, linklinepPaint);
				isLink = false;
			}
			//**********************************************
		}
		//绘制顶部空心圆
		canvas.drawCircle(topcircleX, topcircleY, Default_topcircleR, circlePaintF);
		canvas.drawCircle(topcircleX, topcircleY, Default_topcircleR, circlePaint);
		//绘制顶部文本
		canvas.drawText(toptvText, toptvX, toptvY, countPaint);
		//绘制底部红色框
		if (isSelect) {
			if (isYear) {
				//若是年份则绘制成圆角矩形
				bottomTvPaint.setColor(mContext.getResources().getColor(R.color.TextColorWhite));
				RectF rectF  = new RectF(bottomrectfLeft, bottomrectfTop, bottomrectfRight, bottomrectfBottom);
				canvas.drawRoundRect(rectF, 6, 6, BottomcirclePaint);
//				isYear = false;
			}else {
				//若是月日则绘制成圆
				canvas.drawCircle(bottomcircleX, bottomcircleY, bottomcircleR, BottomcirclePaint);
				bottomTvPaint.setColor(mContext.getResources().getColor(R.color.TextColorWhite));
			}
//			isSelect = false;
		}else {
			bottomTvPaint.setColor(mContext.getResources().getColor(R.color.order_num_tv_color));
		}
		//绘制底部文本
		canvas.drawText(bottomtvText, bottomtvX, bottomtvY, bottomTvPaint);
		
		//*********************
	/*	if (count <= xlength) {
			isLink = true;
			invalidate();
		}else {
			isLink = false;
//			isZero = false;
			isSelect = false;
			isYear = false;
		}*/
		if (count > xlength) {
		isLink = false;
//		isZero = false;
		isSelect = false;
		isYear = false;
	}
		//**********************
	}
}