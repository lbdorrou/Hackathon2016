class OmniauthVerificationsControllerController < ApplicationController
	skip_before_filter :restrict_access

	respond_to :json

	def verify_token
		render status: :forbidden unless params[:token]

		token = params[:token]
		response = HTTParty.get("https://www.googleapis.com/oauth2/v2/userinfo",
			headers: {"Access_token"  => token,
				"Authorization" => "OAuth #{token}"})

		if response.code == 200
			data = JSON.parse(response.body)
			@user = User.find_for_verfied_token_response(data.symbolize_keys)
		end

		render :json => @user.api_key.to_json || {}
	end

	private

	def restrict_access
	  authenticate_or_request_with_http_token do |token, options|
	    ApiKey.exists?(access_token: token)
  	end

	def request_error
		render status: :forbidden
	end
end
