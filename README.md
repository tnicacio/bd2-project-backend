# bd2-project-backend

## @Usuario (User)
#### \[GET\] /usuarios 
All users
#### \[GET\] /usuarios/ativos 
Active users 
#### \[GET\] /usuarios/{id} 
User by id
#### \[POST\] /usuarios 
Request body: ```user to be inserted```
#### \[PUT\] /usuarios/{id} 
Update user by id
#### \[PUT\] /usuarios/{id}/inativar 
Inactivate user by id 
<br /><br />

## @Produto (Product)
#### \[GET\] /produtos 
All products 
#### \[GET\] /produtos/ativos 
Active products
#### \[GET\] /produtos/{id} 
Product by id
#### \[POST\] /produtos 
Request body: product to be inserted + user responsible. Something like ```JSON.stringify({...newProduct, usuario: { id: 1}})``` 
#### \[PUT\] /produtos/{id} 
Update product by id. Request body needs to have the user responsible for the update: ```{ "usuario": { "id": 1 } }```
#### \[PUT\] /produtos/{id}/Inativar 
Inactivate product by id. User responsible also needs to be identified inside the request body, just like updating a product by id
<br /><br />

## @Venda (Order)
#### \[GET\] /vendas 
All orders
#### \[GET\] /vendas/{id} 
Order by id
#### \[POST\] /vendas 
Request body: order to be inserted + user responsible. ```JSON.stringify({ ...newOrder, usuario: { id: 1} })```
#### \[PUT\] /vendas/{id}/{confirmar|cancelar}
Confirm or cancel order by id
<br /><br />

## @Itens (Items)
#### \[GET\]  /itens/{vid} 
Item by id
#### \[POST\] /itens/{vid} 
Insert item by id. Request body example:```{ "produto": {"id": 1}, "qtProduto": 2 }) ```
#### \[POST\] /itens/{vid}/postall 
Insert a list of items related to an order id. Request body example: ```\[{"produto":{"id": 1},"qtProduto": 2}, {"produto":{"id": 2},"qtProduto": 3}\]```
<br /><br />

## @Parcelamento (Installment)
#### \[GET\] /parcelamentos 
All installments
#### \[GET\] /parcelamentos/{vid} 
Installment by order id
<br /><br />
