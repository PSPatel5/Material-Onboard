# Material Onboard

This is demo library for personal use to make introduction of your application more beautiful. Developer can add 6 different types of pages in material introduction which are listed in the features section.
A good design is always crucial in software developement process. Google introduced material design with api 21 back in 2015. About designing As Irene au quotes,

> Good design is like a refrigerator. When it works , 
> no one notices but when it doesn’t , it sure stinks.

# Demo

### Vertical Intro
![](https://github.com/techtainer-psp/Material-Onboard/blob/master/pictures/Vertical_color.gif)

### Horizontal Intro
![](https://github.com/techtainer-psp/Material-Onboard/blob/master/pictures/Horizontal_white.gif)

## Features!

  - Default template with basic usage. ( Title , Image , Description )
  - Default template with advance parameters. ( Color, Font , Background , Text Size etc )
  - Permission fragment with basic usage. ( Image , Description , Permission Name )
  - Permission fragment with advance parameters. ( Color, Size , Background )
  - User defined custom fragments.
  - User defined custom layouts.

You can also:
  - Both horizontal and vertical scroll available
  - Show / Hide indicators
  - Show / Hide Previous Button
  - Show / Hide Next Button
  - Show / Hide Skip Button



### Installation

Material intro requies [Design Support Library](https://developer.android.com/topic/libraries/support-library/packages#design)  to run.

Put below line in your project level Gradle.

```Gradle & Maven
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Put below line in your app level Gradle.

```Gradle & Maven
implementation 'com.github.techtainer-psp:Material-Onboard:v1.7'
```

### Other Libraries

Thanks to  [Roman Danylyk](https://github.com/romandanylyk) as i have used his [PageIndicatorView](https://github.com/romandanylyk/PageIndicatorView)  for page indication in this library.
Material Onboard uses these other libraries and they are required to make this library function properly.
* [Design Support Library] - For snakbar when permission is denied by user !
* [Constraint Layout] - For Layout designing.

```Gradle & Maven
implementation 'com.android.support:design:28.0.0'
implementation 'com.android.support.constraint:constraint-layout:1.1.2'
```

## Usage

In your launcher activity Instead of extending AppCompatActivity , extend '**IntroActivity**'. 


##### Method 1
Default Fragment Template
```java
//to create a page

IntroTemplate introTemplate = IntroTemplate.newInstance(TITLE , IMAGE_RESOURCE , DESCRIPTION);

//add it using

addNewPage(introTemplate);
```
##### Method 2
Default Fragment Template With Advance Parameters
```java
//to create a page
    IntroTemplate template = new IntroTemplate();
    template.setTitle(String title);
    template.setTitleColor(int color);
    template.setTitleSize(float size);
    template.setTitleTypeFace(int typeface);
    template.setContent(String description);
    template.setContentColor(int color);
    template.setContentSize(float size);
    template.setContentTypeFace(int typeface);
    template.setImage(int image_resouce);
    template.setBackgroundColor(int color);
    
//add it using
    addNewPage(IntroTemplate.newInstance(template));
```

##### Method 3
Default Permission Slide
```java
//to create a page
    PermissionTemplate template = PermissionTemplate.newInstance(IMAGE_RESOURCE, DESCRIPTION , PERMISSION);  
    // example - PERMISSION =  Manifest.permission.ACCESS_FINE_LOCATION;
//add it using
    addNewPage(template);
```
##### Method 4
Default Permission Slide with advance parameters
```java
//to create a page

        PermissionTemplate template = new PermissionTemplate();
        template.setPermissions(String permission);
        template.setImageResource(int resource);
        template.setDescription(String description);
        template.setButtonColor(Color.BLUE);
        template.setButtonSize(18f);
        // example - PERMISSION =  Manifest.permission.ACCESS_FINE_LOCATION;
        // write template. and hit ctrl+space for more options.
        
//add it using
    addNewPage(PermissionTemplate.newInstance(template));
```

##### Method 5
User Defined Fragments
```java
//to create a page
    CustomFragment fragment = new CustomFragment();
//add it using
    addNewPage(fragment);
```
##### Method 6
Using layout resource file only
```java
//to create a page
    int resouce_id = R.layout.layout_resource_id;
//add it using
    addNewPage(resource_id);
```
#### Extra Feautures

By extracting '**IntroActivity**' , you will be overriding 3 factory methods which are
- public boolean isVertical();           /// Wether to scroll horizontally or vertically
- public void onDonePressed();        /// What to do on Click Done on Last page of Introduction
- public void onSkipPressed();        /// What to do on Click of Skip Button

This three Methods are compulsory other optional methods are:

- showIndicators(boolean visibility);       // show/hide page indicators
- showPreviousButton(boolean visibility);   // show/hide  previous button
- showSkipButton(boolean visibility);       // show/hide skip button
- showNextButton(boolean visibility);       // show/hide next button.

### Issues
While using this library if you face any issues report use at techtainer.psp@gmail.com

### Licence 

It is free library for everyone use it as you required.

 
