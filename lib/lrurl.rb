# encoding: utf-8

require 'uri'
require 'open-uri'
require 'domainatrix'

module LubyRuffyUrl
	def host_of_url(url)
	  begin
	    url = 'http://'+url+'/' if !url.include?('http://') and !url.include?('https://')
	    url = URI.encode(url) unless url.include? '%' #如果包含百分号%，说明已经编码过了
	    uri = URI(url)
	    uri.host
	  rescue => e
	    nil
	  end
	end

	def hosts_of_urls(urls)
	  hosts = urls.collect {|u| 
	    host_of_url(u)
	  }.uniq
	  hosts
	end


	def domains_of_hosts(hosts)
	end

	def ips_of_hosts(hosts)
	end

end