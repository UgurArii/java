
const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');
canvas.height = window.innerHeight;
canvas.width =  window.innerWidth;
const gravity = 0.4;
let jumpCount = false;
let jumpCountE = false;
const keys = {
left: false,
right: false,
space: false,
keyE: false,
r: false
}
let playerCont = false;
let enemyCont = true;
class Player{
    constructor(){
        this.position = {
            x:50,
            y:10
        }
        this.velocity ={
        x:0,
        y:0
        }
        this.height = 70;
        this.width = 70;
    }
    draw(){
    ctx.fillStyle = 'red';        
    ctx.fillRect(this.position.x, this.position.y,this.width, this.height);
    }
    update(){
        this.draw();
        this.position.y += this.velocity.y;
        this.position.x += this.velocity.x;
      
        if (this.position.y + this.height + this.velocity.y <= canvas.height) {
            this.velocity.y += gravity;
        }
    }
}
class Rect2{
    constructor(x,y){
        this.position = {
            x: x,
            y: y
        }
        this.velocity ={
        x:0,
        y:0
        }
        this.height = 60;
        this.width = 500;

    }
    draw(){
     ctx.fillStyle = 'black';   
    ctx.fillRect(this.position.x, this.position.y,this.width, this.height);
    
    }
    update(){
        this.draw();
        this.position.y += this.velocity.y;
        this.position.x += this.velocity.x;
    }
}
class Enemy{
    constructor(x, y){
        this.position = {
            x:x,
            y:y
        }
        this.velocity ={
        x:0,
        y:0
        }
        this.height = 70;
        this.width = 70;
    }
    draw(){
    ctx.fillStyle = 'pink';        
    ctx.fillRect(this.position.x, this.position.y,this.width, this.height);
    }
    update(){
        this.draw();
        this.position.y += this.velocity.y;
        this.position.x += this.velocity.x;
      
        if (this.position.y + this.height + this.velocity.y <= canvas.height) {
            this.velocity.y += gravity;
        }
    }
}

ctx.clearRect(0,0,canvas.width,canvas.height);
let platforms = [new Rect2(0,canvas.height - 60) ,new Rect2(500,canvas.height - 60),new Rect2(1000,canvas.height - 60),new Rect2(1750,canvas.height - 60)];
const enemy = new Enemy(100,100);
const player = new Player();

function animate(){
    requestAnimationFrame(animate);
    ctx.clearRect(0,0,canvas.width,canvas.height);

    player.update();
  platforms.forEach(platform =>{
platform.update();
  })

    enemy.update();


    if (player.position.y  > canvas.height || enemy.position.y > canvas.height) {
        jumpCount = false;
        jumpCountE = false;
        location.reload();
     }
    //   if (player.position.x > platforms.position.x + platforms.width || 
    //      player.position.x + player.width < platforms.position.x ||
    //      player.position.y  + player.height > platforms.position.y + platforms.height || 
    //      player.position.y + player.height + player.velocity.y < platforms.position.y 
    //     ) {
        
    //   }else{
    //     player.velocity.y = 0;
    //     jumpCount = true;
    //  }
    platforms.forEach((platform) =>{
      
            if (enemy.position.x > platform.position.x + platform.width || 
                enemy.position.x + enemy.width < platform.position.x ||
                enemy.position.y  > platform.position.y + platform.height || 
                enemy.position.y + enemy.height + enemy.velocity.y < platform.position.y 
               ) {
               
             }else{
                jumpCountE = true;
                enemy.velocity.y = 0;
            }
  })
    //  }


            if (player.position.x > enemy.position.x + enemy.width || 
                player.position.x + player.width < enemy.position.x
                  ||
                 player.position.y + player.height  > enemy.position.y + enemy.height || 
                 player.position.y + player.height + player.velocity.y < enemy.position.y 
               ) {
               
               }

           else{player.velocity.y = 0;
            enemy.velocity.y = 0;
                jumpCount = true;
                jumpCountE = true;
                    player.velocity.x = 0;
                }  
        
                if (enemy.position.x > player.position.x + player.width || 
                    enemy.position.x + enemy.width < player.position.x
                      ||
                     enemy.position.y + enemy.height  > player.position.y + player.height || 
                     enemy.position.y + enemy.height + enemy.velocity.y < player.position.y 
                   ) {
                   
                   }
    
               else{player.velocity.y = 0;
                enemy.velocity.y = 0;
                    jumpCount = true;
                    jumpCountE = true;
                        player.velocity.x = 0;
    
                    }  






    
    platforms.forEach((platform) =>{
        if (player.position.x > platform.position.x + platform.width || 
            player.position.x + player.width < platform.position.x ||
            player.position.y  > platform.position.y + platform.height || 
            player.position.y + player.height + player.velocity.y < platform.position.y 
           ) {
           
         }else{
           player.velocity.y = 0;
           jumpCount = true;
        }
          })
  
if (keys.r === true) {
if(enemyCont === true){playerCont = true; enemyCont = false}
else if(playerCont === true){enemyCont = true;playerCont = false;}
}

if (playerCont === true) {
    if (keys.space === true) {
        if (jumpCount == true) {
        jumpCount = false;
         player.velocity.y = -10;   
         }
       }
    if (keys.right === true && player.position.x < 400) {
        player.velocity.x = 5;
       } else if (keys.left === true  && player.position.x > 100) {
        player.velocity.x = -5;

       }else{player.velocity.x = 0}

       if (keys.right === true) {
        platforms.forEach((platform) =>{
            platform.velocity.x = -5;
        })
            enemy.velocity.x = -5;

       }else if (keys.left === true) {
   platforms.forEach((platform) =>{
     platform.velocity.x = 5;
 })

    enemy.velocity.x = 5;

}else{  platforms.forEach((platform) =>{
    platform.velocity.x = 0;
})
enemy.velocity.x = 0;
}
}else if(enemyCont === true){
    
    if (keys.space === true) {
        if (jumpCountE === true) {
         jumpCountE = false;
         enemy.velocity.y = -10;   
         }
       }
    if (keys.right === true && player.position.x < 400) {
        enemy.velocity.x = 5;
       } else if (keys.left === true  && player.position.x > 100) {
        enemy.velocity.x = -5;

       }else{enemy.velocity.x = 0}

       if (keys.right === true) {
        platforms.forEach((platform) =>{
            platform.velocity.x = -5;
        })
       
            player.velocity.x = -5;

       }else if (keys.left === true) {
   platforms.forEach((platform) =>{
     platform.velocity.x = 5;
 })

    player.velocity.x = 5;
}else{  platforms.forEach((platform) =>{
    platform.velocity.x = 0;
})
    player.velocity.x = 0;
}
}
}

     

animate();

 addEventListener('keydown', (event) =>{
         switch (event.key) {
         case " ":
         case "w":
        keys.space = true;
         break;
         case "ArrowRight" :
         case "d":
        keys.right = true;
         break;
         case "ArrowLeft" :
            case "a":
           keys.left = true;
            break;
            case "r":
            keys.r = true;
            
            break;
         }})

  addEventListener('keyup', (event) =>{
         switch (event.key) {
         case " ":
         case "w":
        keys.space = false;
         break;
         case "ArrowRight" :
         case "d":
        keys.right = false;
         break;
         case "ArrowLeft" :
            case "a":
           keys.left = false;
            break;
            case "r":
                keys.r = false;
                break;
         }
         
         
        })

window.onkeydown = move = (e) => {
    let key = e.keyCode;
    if     (key === 68 && player.x <= canvas.width-25) {player.x += 10;} //right
    else if(key === 65 && player.x >= 10) {player.x -= 10;} //left
    else if(key === 83 && player.y <= canvas.height-25) {player.y += 10;} //down
    else if(key === 87 && player.y >= 10) {player.y -= 10;} //up

    ctx.clearRect(0,0, canvas.width, canvas.height);
    ctx.fillRect(player.x, player.y, player.w, player.h);
}

    

// switch (event.key) {
    //     case " ":
    //     case "w":
         
    //         if (jumpCount == true) {
    //             jumpCount = false;
    //             player.velocity.y = -15;   
         
    //         }
    //         break;
    //         case "ArrowRight" :
    //         case "d":
    
    //   player.velocity.x = 5;
    //   platforms.forEach((platform) =>{
    //     platform.position.x -= 10;
    // })
    //         break
    //         case "ArrowLeft" :
    //             case "a":
    //                 if(player.position.x > 160){
    //            player.velocity.x = -5;
              
    //       platforms.forEach((platform) =>{
    //         platform.position.x += 10;
    //          console.log(platform.position.x)
    //       })
    //                 }
    //    break
    //    case 'e':
    //     player.width = 200
    //    break
    //     }






    //     switch (event.key) {
//         case "ArrowRight" :
//             case "d":
//       player.velocity.x = 0;
//             break
//             case "ArrowLeft" :
//                 case "a":
//           player.velocity.x = 0;
//                 break
//                 case 'e':
//                     player.width = 70;
//                    break
// }
