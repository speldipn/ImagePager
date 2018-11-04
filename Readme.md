# ImagePager

### 동작 시현

![](/screenshot/image_pager.gif)

* 프래그먼트를 사용하는 ViewPager가 아닌 ViewPager의 원래 용도 그래도 단순 페이징 하는 동작을 하는 예제를 작성

### CustomAdapter

* PagerAdapter를 상속하여 2개의 메소드를 오버라이딩해줘야 한다.
  * getCount : 페이저의 개수를 반환하는 메소드
  * isViewFromObject : 비교를 하는 Object가 현재 페이저에 보이는 View인지를 확인하는 메소드
* 기본으로 오버라이딩하는 함수 외에 생성자를 포함해서 추가적으로 2개의 메소드를 저 정의해줘야 한다.
  * 생성자에서는 Context 관련 함수들을 편히 사용할 수 있도록 생성자를 통해 미리 받아오고 이미지의 경로를 갖고 있는 리스트를 인자로 넘겨받는다
  * instantiateItem : 리스트뷰에서 getView와 같은 동작을 하는 메소드
  * destroyItem : ViewPager는 현재 보여질 페이지의 양 옆의 페이지들을 함께 생성하는데, 현재 페이지와 양 사이드의 2개를 포함한 총3개의 페이지외에 할당된 페이지들을 제거해주는 함수

````java

class CustomAdapter extends PagerAdapter {

    Context context;
    List<String> list;

    public CustomAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pager_item, null);

        ImageView imageView = view.findViewById(R.id.imageView);
        int resId = context.getResources().getIdentifier(list.get(position), "drawable", context.getPackageName());
        imageView.setImageResource(resId);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}

````

### ViewPager.OnPageChangeListener

* 페이지 이동이 감지되었을때 이를 확인할 수 있는 리스너로 총 3개의 오버라이딩 메소드가 있으며, 자주 사용되는 것은 onPageSelected 메소드로 페이지 이동이 완료된 직후에 호출되는 함수이다.

````java
viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
````




