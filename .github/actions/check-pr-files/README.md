# check-pr-files action

This action checks if a PR contains necessary files.

## Inputs

### `files`

**Required**

A list of files to check. Default:

    ./zktest/src/archive/test2/config.properties
    ./zkdoc/release-note

### `token`

**Required**

A `GITHUB_TOKEN` used to add warning to the PR.

## Example usage

``` yml
uses: ./.github/actions/check-pr-files
with:
  files: |
    README.md
  token: ${{ secrets.GITHUB_TOKEN }}
```