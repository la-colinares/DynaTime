# DynaTime
- DynaTime is Countdown Timer based on Hourglass library which has a Pause, Resume features,
the main differences of this library are stopping the loop of timer when finished and all the functions written in single abstract class.

![dynatime](https://user-images.githubusercontent.com/28802529/49686942-e2ff7b80-fb36-11e8-9115-d786e8fa8df2.gif)

# Instructions
Add this to your build.gradle

```
compile 'com.github.la-colinares:DynaTime:1.0.0'
```
OR
```
implementation 'com.github.la-colinares:DynaTime:1.0.0'
```

# Usage
- Three constructors for initializing

        public DynaTime()       //default interval is 1 second
        public DynaTime(time)   //default interval is 1 second 
        public DynaTime(time, interval)  
- Functions to manage timer

        public void startTimer()
        public void stopTimer()
        public void pauseTimer()
        public void resumeTimer()
- Abstract methods for updating UI

        public void onTimerStart(long timeRemaining)    // Called after every interval.
        public void onFinish()                          // Called when time specified finishes 
- Setters for setting time and interval

        public void setTime(long timeInMillis)
        public void setInterval(long intervalInMillis)
Examples
Here is an example:
- Just initialize an object of DynaTime.

        DynaTime dynaTime = new DynaTime(25000, 1000) {
            @Override
            public void onTimerStart(long timeRemaining) {
                String min = (timeRemaining%60000/1000) != 0 ? (timeRemaining%60000/1000) + "" : (timeRemaining%60000/1000) + "0";
                String time = (timeRemaining/60000) + ":" + min;
                Toast.show(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.show(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        };
        
        dynaTime.startTimer();

## Credits
- Credits to [Hourglass Library](https://github.com/groverankush/Hourglass) 

## Developer
- [Lowi Adrian Colinares](https://github.com/la-colinares)

## License
- This project is licensed under MIT License, - see the [LICENSE](https://github.com/la-colinares/DynaTime/blob/master/LICENSE) file for details

