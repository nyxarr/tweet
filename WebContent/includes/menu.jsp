<div id="shadow-popup" style="display: none; z-index: 99;"></div>
	<div class="login-box" style="display: none; z-index: 100;">
		<form id="login-form" method="get">
			<fieldset class="form-group">
				<input class="form-control" id="username" type="text" placeholder="Username" required>
				<input class="form-control" id="password" type="password" placeholder="Password" required>
				<button type="button" class="btn btn-primary" id="login-submit">Login</button>
				<a id="register" href="javascript:void(0)">or register</a>
			</fieldset>
		</form>
		<form id="register-form" method="get" style="display: none;">
			<fieldset class="form-group">
				<input class="form-control" id="firstname-register" type="text" placeholder="Firstname" required>
				<input class="form-control" id="lastname-register" type="text" placeholder="Lastname" required>
				<input class="form-control" id="username-register" type="text" placeholder="Username" required>
				<input class="form-control" id="password-register" type="password" placeholder="Password" required>
				<input class="form-control" id="password-confirm" type="password" placeholder="Confirm password" required>
				<input class="form-control" id="email-register" type="email" placeholder="Email" required>
				<button type="submit" class="btn btn-primary" id="register-submit">Register</button>
			</fieldset>
		</form>
	</div>
<div id="leftside-menu" class="col-md-2">
	<nav id="leftmenu" class="navbar navbar-default">
		<div id="container-leftmenu" class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<div id="leftmenu-ul" class="navbar-header">
					<ul class="nav navbar-default">
						<li>
							<a href="/tweet/">
								<span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home
							</a>
						</li>
						<li class="show-login">
							<a href="javascript:void(0)">
								<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>Login
							</a>
						</li>
						<li>
							<a href="account.jsp">
								<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>Account
							</a>
						</li>
						<li>
							<a id="show-friends" href="#">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>Friends
							</a>
							<span id="friend-count" class="badge"></span>
							<div id="friends-panel" style="display: none;">
								<button type="button" class="btn btn-primary" id="add-friend-button">
									<span class="glyphicon glyphicon-plus"></span>Add Friend
								</button>
								<form id="add-friend-form" style="display: none;" method="get">
									<div class="form-group">
										<input type="text" class="form-control" id="friend-add-input">
									</div>
								</form>
								<ul></ul>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
</div>