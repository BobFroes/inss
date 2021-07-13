<p><strong>Microsservi&ccedil;o INSS</strong>&nbsp;</p>
<p style="text-align: justify;">Se voc&ecirc; &eacute; ou j&aacute; foi contratado em regime de CLT, pode ter percebido em seu contra-cheque alguns descontos de inss (seguridade social), irf (imposto de renda), sal&aacute;rio familia etc. Este projeto &eacute; um microsservi&ccedil;o respons&aacute;vel por calcular os descontos de inss de uma determinada lista de funcion&aacute;rios com seus respectivos sal&aacute;rios. A tabela a seguir ser&aacute; nossa refer&ecirc;ncia para os c&aacute;lculos.</p>
<table style="width: 100%;">
    <tbody>
        <tr>
            <td style="width: 50.0000%;"><span style='color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'><strong>Sal&aacute;rio de contribui&ccedil;&atilde;o 2021</strong></span><strong><br></strong></td>
            <td style="width: 50.0000%;"><strong>Al&iacute;quota</strong></td>
        </tr>
        <tr>
            <td style="width: 50.0000%;"><span style='color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>At&eacute; R$ 1.100</span></td>
            <td style="width: 50.0000%;">7,50%</td>
        </tr>
        <tr>
            <td style="width: 50.0000%;"><span style='color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>De R$ 1.100,01 a R$ 2.203,48</span></td>
            <td style="width: 50.0000%;">9,00%</td>
        </tr>
        <tr>
            <td style="width: 50.0000%;"><span style='color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>De R$ 2.203,49 at&eacute; R$ 3.305,22</span></td>
            <td style="width: 50.0000%;">12,00%</td>
        </tr>
        <tr>
            <td style="width: 50.0000%;"><span style='color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji"; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>De R$ 3.305,23 at&eacute; R$ 6.433,57 ou mais</span></td>
            <td style="width: 50.0000%;">14,00%</td>
        </tr>
    </tbody>
</table>
<p dir="ltr" style="line-height:1.38;text-align: justify;margin-top:0pt;margin-bottom:0pt;"><span style="font-size:11pt;font-family:Arial;color:#000000;background-color:transparent;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">Como exemplo, se uma pessoa ganha R$ 3.000,00, entrar&aacute; na terceira faixa e ter&aacute; um desconto de 12% resultando em R$ 360,00 reais.</span></p>
<p dir="ltr" style="line-height:1.38;text-align: justify;margin-top:0pt;margin-bottom:0pt;">Suponhamos que um outro microsservi&ccedil;o (Rh Microservice) ir&aacute; enviar uma mensagem contendo a lista de funcion&aacute;rios que ser&aacute; processada pela nossa classe consumidora (Consumer):</p>


	{
	   "year": "2021", 
       "employees": [{
		   "id": "fa07de98-1d78-4b8a-9fb2-0308474d3c35",
		   "salary": 1100
	   }, {
		   "id": "7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7",
		   "salary": 2000
	   }, {
		   "id": "df32e121-03a7-4af4-b5c5-02ffc08b3db5",
		   "salary": 3000
	   }, {
		   "id": "f048fe759-02ba-4e25-b19f-4c4c882d4d2",
		   "salary": 7000
	   }]
    }

<p>A classe Consumer &eacute; respons&aacute;vel por converter a lista em um objeto de request (CalculateRequest) que ser&aacute; passado para uma service <span style='color: rgb(0, 0, 0); font-family: "Times New Roman"; font-size: medium; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>(CalculateService)&nbsp;</span>respons&aacute;vel por calcular todos os descontos de cada funcion&aacute;rio. Ao final do processamento da service o Consumer ir&aacute; produzir uma lista de funcion&aacute;rios com seus respectivos descontos de inss para o suposto microsserviço (Rh Microservice) que solicitou o cálculo. </p>

	{
	   "year": "2021", 
       "employees": [{
		   "id": "fa07de98-1d78-4b8a-9fb2-0308474d3c35",
		   "salary": 1100
		   "discount": 82.50
		   "percent": 7.50
	   }, {
		   "id": "7c1e1d02-0a0b-41c7-b5f1-929ec01e04d7",
		   "salary": 2000
		   "discount": 180.00
		   "percent": 9.00
	   }, {
		   "id": "df32e121-03a7-4af4-b5c5-02ffc08b3db5",
		   "salary": 3000
		   "discount": 360.00
		   "percent": 12.00
	   }, {
		   "id": "f048fe759-02ba-4e25-b19f-4c4c882d4d2",
		   "salary": 7000
		   "discount": 980.00
		   "percent": 14.00
	   }]
    }

<p>Foram desenvolvidos testes de unidade e de integra&ccedil;&atilde;o para este projeto</p>
<ul>
    <li>Teste de unidade utilizando Mockito</li>
    <li>Teste de integra&ccedil;&atilde;o utilizando Test Container (PostgreSQL) e @EmbebbedleKafka</li>
</ul>
