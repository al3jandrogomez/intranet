
<html>
    <head>
        <title>Validación de Personal Instituto de la Defensoría</title>
        <style>
            body{
                margin: 0px;
                padding: 0px;
                background: #f1f1f1;
                font-family: arial;
                box-sizing: border-box;

            }
            .card-container{
                width: 300px;
                height: 430px;
                background: #fff;
                border-radius:  6px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform:  translate(-50%, -50%);
                box-shadow:  0px 1px 10px 1px #000;
                overflow: hidden;
                display: inline-block;
            }
            .upper-container{
                height: 150px;
                background-image: url(../resources/images/logos-idp-edomex.png);
                background-repeat: no-repeat;
                background-position: center;    
                background-size: contain;
            }
            .lower-container{
                height: 280px;
                background: #9fc12a;
                padding: 20px;
                padding-top: 40px;
                text-align:  center;
            }
            .lower-container h3, h4 {
                box-sizing: border-box;
                height: auto;
                line-height: 1;
                font-weight: bold;
                color: white;
            }
            
            .lower-container p {
                font-size: 16px;
                color: #67686d;
                margin-bottom: 30px;
                font-weight: bold;
            }
            
            .lower-container .btn {
                padding: 12px 20px;
                background: ${color};
                border: none;
                color: white;
                border-radius: 30px;
                font-size: 12px;
                text-decoration: none;
                font-weight: bold;
            }
            
            .lower-container .btn:hover {
                background: transparent;
                color: #fff;
                border: 2px solid #58ba47;
            }
            .lower-container  h4 {
                color: #67686d;
                /*opacity: .6;*/
                font-weight: bold;
            }
            .image-container{
                background:  white;
                width: 80px;
                height: 80px;
                border-radius: 50%;
                padding: 5px;
                transform: translate(100px,100px);
            }
            .image-container img{
                
                width: 80px;
                height: 80px;
                border-radius: 50%;
            }

        </style>
    </head>

    <body>
        <div class="card-container">
            <div class="upper-container">
                
                <div class="image-container">
                    <img src=${imagen}>
                </div>   
            </div>

            <div class="lower-container">
                <div>
                    <h3>${nombre}</h3>
                    <h4>${cargo}</h4>
                </div>
                <div>
                    <p> 
                        ${municipio}
                    </p>
                </div>
                <div>
                    <a href="#" class="btn"></a>
                </div>
            </div>

        </div>
    </body>
</html>