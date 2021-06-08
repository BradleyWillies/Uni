<!DOCTYPE html>
<html>
	<head>
		<title>All Accounts</title>
	</head>
	<body>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>account no</th>
					<th>type</th>
					<th>balance</th>
					<th>interest</th>
				</tr>
			</thead>
			<tbody>
				@foreach($accounts as $account)
				<tr>
					<td> {{ $account->id }} </td>
					<td> {{ $account->accountno }} </td>
					<td> {{ $account->type }} </td>
					<td> {{ $account->balance }} </td>
					<td> {{ $account->interest }} </td>
				</tr>
				@endforeach
			</tbody>
		</table>
	</body>
</html>