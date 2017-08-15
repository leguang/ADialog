# ADialog

[![Release](https://jitpack.io/v/leguang/ADialog.svg)](https://jitpack.io/#leguang/ADialog)

StateManager是一个页面状态管理工具，可以让开发者方便而又灵活地切换界面的状态。（欢迎Star一下）
## 能做什么？([下载 apk](https://github.com/leguang/BaseDialogFragment/blob/master/app-debug.apk))
- **只需要通过传入布局ID即可定制出自己的DialogFragment**
- **弹框位置可配置，在顶部、在中间还是在底部由你决定**
- **默认提供一些进入动画**
- **简洁的API，简单的配置**
- **任何布局数据的填充和的事件都可以通过ViewHolder传递出来**

## 如何使用它？

1. 先在项目目录下的的build.gradle 的 repositories 添加:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

2. 然后在App目录下的dependencies添加:
```
	dependencies {
	     //简易的配置DialgoFragment工具
   		 compile 'com.github.leguang:BaseDialogFragment:1.1'
	}
```
此时同步一下，即已完成引入。

3. 代码中简单使用：

```
//首先配置一下：
new BaseDialogFragment()
        .setLayoutId(R.layout.friend_set_layout)
        .setConvertListener(new BaseDialogFragment.ConvertListener() {
            @Override
            public void convert(BaseViewHolder holder, BaseDialogFragment dialog) {
                holder.setOnClickListener(R.id.setting0, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("点了");
                    }
                });
            }
        })
        .setDimAmount(0.3f)
        .setHeight(310)
        .setGravity(Gravity.BOTTOM)
        .setAnimStyle(R.style.SlideAnimation)
        .show(getSupportFragmentManager());
```

## 高级用法：
当然你也可以通过继承BaseDialogFragment来改造属于自己的对话框。

>**持续更新!，欢迎Issues+Star项目**

## 感谢
[Alex-Cin/Dialog](https://github.com/Alex-Cin/Dialog)

[CymChad/BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

## License

```
Copyright 2016 李勇

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
