outlets = 4;

function list(x,y,position,xoffset, yoffset)
{
  // Pay attention to when the button is pressed.  Ignore when it is released
  if(position != 1 )
      return;

   // The first outlet is for normal fire events.  The second outlet is for stop events
   if (x == (6 + xoffset)) { // Operating on scenes
        if( y == (7 + yoffset )) {
            outlet(3, y);
        } else {
            outlet(2, y);
        }
    } else { // Operating on clips
       if( y == (7 + yoffset )) {
            outlet(1, x);
       } else {
             outlet(0, [x,y]);
       }
    }
}
list.immediate = 1;
